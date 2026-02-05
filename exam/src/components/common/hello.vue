<template>
  <section class="index">
    <div class="hello">
      <i class="iconfont icon-xihuan"></i><span>很高兴遇见你,{{user.userName}}老师。</span>
    </div>
    <div class="msg">
      <p class="title">教务公告：</p>
      <ul>
        <li v-if="noticeList.length === 0" class="empty">暂无公告</li>
        <li v-for="item in noticeList" :key="item.noticeId" @click="openMsg(item)">
          <i class="iconfont icon-flag"></i>{{ item.content || '未填写内容' }}
        </li>
      </ul>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      user: { //用户信息
        userName: null,
        userId: null
      },
      noticeList: [] //公告列表
    }
  },
  created() {
    this.getUserInfo()
    this.fetchNotices()
  },
  methods: {
    getUserInfo() { //获取用户信息
      let userName = this.$cookies.get("cname")
      let userId = this.$cookies.get("cid")
      this.user.userName = userName
      this.user.userId = userId
    },
    // 拉取公告列表（取最新3条）
    fetchNotices() {
      this.$axios(`/api/notices/1/3`).then(res => {
        const data = res.data && res.data.data
        // 接口返回的是分页对象 {records, total, ...}
        this.noticeList = (data && data.records) ? data.records : []
      }).catch(() => {
        this.noticeList = []
      })
    },
    openMsg(item) {
      this.$alert(item.content || '暂无内容', '教务公告', {
        confirmButtonText: '确定'
      })
    }
  }
}
</script>


<style lang="less" scoped>
.index {
  margin-left: 70px;
  .hello {
    font-size: 20px;
    color: #726f70;
    .icon-xihuan {
      font-size: 30px;
      color: #dd6572;
    }
  }
  .msg {
    .title {
      font-size: 16px;
      color: #000;
      margin-top: 20px;
      margin-left: 10px;
    }
    ul {
      display: flex;
      flex-direction: column;
      width: 200px;
      overflow: hidden;
    }
    li {
      margin-top: 10px;
      font-size: 14px;
      color: lightcoral;
      cursor: pointer;
      display: inline-block;
    }
    .empty {
      color: #999;
      cursor: default;
    }
  }
}
</style>

