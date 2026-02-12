<!-- AI 智能助手聊天页面 -->
<template>
  <div class="ai-chat">
    <div class="chat-header">
      <i class="el-icon-s-opportunity"></i>
      <span>AI 智能助手</span>
      <span class="sub">（基于通义千问）</span>
    </div>

    <!-- 聊天消息区域 -->
    <div class="chat-body" ref="chatBody">
      <!-- 欢迎消息 -->
      <div class="msg-item ai" v-if="messages.length === 0">
        <div class="avatar ai-avatar">AI</div>
        <div class="bubble">你好！我是 AI 智能助手，有什么可以帮你的吗？</div>
      </div>
      <!-- 历史消息列表 -->
      <div
        class="msg-item"
        :class="msg.role"
        v-for="(msg, index) in messages"
        :key="index"
      >
        <div class="avatar" :class="msg.role === 'user' ? 'user-avatar' : 'ai-avatar'">
          {{ msg.role === 'user' ? '我' : 'AI' }}
        </div>
        <div class="bubble">
          <span v-if="msg.role === 'ai' && msg.loading" class="loading-dots">思考中...</span>
          <span v-else style="white-space: pre-wrap;">{{ msg.content }}</span>
        </div>
      </div>
    </div>

    <!-- 输入区域 -->
    <div class="chat-footer">
      <el-input
        type="textarea"
        v-model="inputMsg"
        :rows="2"
        placeholder="请输入你的问题..."
        @keyup.enter.native="handleEnter"
        :disabled="isLoading"
      ></el-input>
      <el-button
        type="primary"
        icon="el-icon-s-promotion"
        @click="sendMessage"
        :loading="isLoading"
        :disabled="isLoading || !inputMsg.trim()"
      >发送</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'aiChat',
  data() {
    return {
      inputMsg: '',       // 输入框内容
      messages: [],       // 聊天记录数组，每条格式 { role: 'user'|'ai', content: '文本' }
      isLoading: false    // 是否正在等待 AI 回复
    }
  },
  methods: {
    // 发送消息
    async sendMessage() {
      const text = this.inputMsg.trim()
      if (!text) return

      // 1. 把用户消息添加到聊天记录
      this.messages.push({ role: 'user', content: text })

      // 2. 清空输入框
      this.inputMsg = ''

      // 3. 添加一条"思考中"的 AI 占位消息
      const aiMsg = { role: 'ai', content: '', loading: true }
      this.messages.push(aiMsg)
      this.isLoading = true

      // 4. 滚动到底部
      this.scrollToBottom()

      // 5. 调用 FastAPI 接口（非流式，一次性返回）
      try {
        const res = await this.$axios.post('http://localhost:8000/ask', {
          question: text
        })
        aiMsg.content = res && res.data && res.data.answer ? res.data.answer : '未获取到回答。'
      } catch (error) {
        aiMsg.content = '请求失败，请稍后重试。'
      } finally {
        aiMsg.loading = false
        this.isLoading = false
        this.scrollToBottom()
      }
    },
    // 按回车发送（Shift+Enter换行）
    handleEnter(e) {
      if (!e.shiftKey) {
        e.preventDefault()
        this.sendMessage()
      }
    },
    // 聊天区域滚动到底部
    scrollToBottom() {
      this.$nextTick(() => {
        let body = this.$refs.chatBody
        if (body) {
          body.scrollTop = body.scrollHeight
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.ai-chat {
  display: flex;
  flex-direction: column;
  height: calc(100vh - 120px);
  margin: 0 40px;
  border: 1px solid #e4e7ed;
  border-radius: 8px;
  overflow: hidden;
  background: #fff;
}

.chat-header {
  padding: 14px 20px;
  background: linear-gradient(135deg, #409EFF, #66b1ff);
  color: #fff;
  font-size: 18px;
  font-weight: bold;
  i {
    margin-right: 6px;
    font-size: 22px;
  }
  .sub {
    font-size: 12px;
    font-weight: normal;
    opacity: 0.8;
    margin-left: 8px;
  }
}

.chat-body {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
  background: #f5f7fa;
}

.msg-item {
  display: flex;
  margin-bottom: 16px;
  align-items: flex-start;

  &.user {
    flex-direction: row-reverse;
    .bubble {
      background: #409EFF;
      color: #fff;
      border-radius: 12px 2px 12px 12px;
      margin-right: 10px;
      margin-left: 60px;
    }
  }
  &.ai {
    .bubble {
      background: #fff;
      color: #333;
      border: 1px solid #e4e7ed;
      border-radius: 2px 12px 12px 12px;
      margin-left: 10px;
      margin-right: 60px;
    }
  }
}

.avatar {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  font-weight: bold;
  flex-shrink: 0;
}
.user-avatar {
  background: #409EFF;
  color: #fff;
}
.ai-avatar {
  background: #67c23a;
  color: #fff;
}

.bubble {
  padding: 10px 14px;
  font-size: 14px;
  line-height: 1.6;
  max-width: 70%;
  word-wrap: break-word;
  box-shadow: 0 1px 3px rgba(0,0,0,0.06);
}

.loading-dots {
  color: #999;
  animation: blink 1.2s infinite;
}
@keyframes blink {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.3; }
}

.chat-footer {
  display: flex;
  padding: 12px 16px;
  border-top: 1px solid #e4e7ed;
  background: #fff;
  align-items: flex-end;
  gap: 10px;
  .el-textarea {
    flex: 1;
  }
  .el-button {
    height: 54px;
  }
}
</style>
