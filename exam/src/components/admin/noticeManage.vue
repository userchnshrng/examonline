<!-- 公告管理页面 -->
<template>
  <div class="all">
    <div class="title">
      <el-button type="primary" icon="el-icon-plus" @click="dialogVisible = true">发布新公告</el-button>
    </div>
    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="content" label="公告内容" width="600"></el-table-column>
      <el-table-column prop="createTime" label="发布时间" width="200"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="deleteNotice(scope.row.noticeId)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[4, 8, 10, 20]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total" class="page">
    </el-pagination>

    <!-- 发布公告的对话框 -->
    <el-dialog title="发布新公告" :visible.sync="dialogVisible" width="30%">
      <el-form>
        <el-form-item label="公告内容">
          <el-input type="textarea" v-model="form.content" rows="4"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addNotice">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pagination: { //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 4, //每页条数
        records: [] //数据列表
      },
      dialogVisible: false, //对话框是否显示
      form: {
        content: ''
      }
    };
  },
  created() {
    this.getNotice();
  },
  methods: {
    getNotice() {
      //调用后端接口分页查询公告
      this.$axios(`/api/notices/${this.pagination.current}/${this.pagination.size}`).then(res => {
        this.pagination = res.data.data;
      }).catch(error => {});
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getNotice();
    },
    //改变当前页码
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getNotice();
    },
    //发布公告
    addNotice() {
      if(this.form.content == '') {
        this.$message.error('内容不能为空');
        return;
      }
      let date = new Date();
      let now = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      this.$axios({
        url: '/api/notice',
        method: 'post',
        data: {
          content: this.form.content,
          createTime: now
        }
      }).then(res => {
        if(res.data.code == 200) {
          this.$message.success('发布成功');
          this.dialogVisible = false;
          this.form.content = ''; //清空
          this.getNotice(); //刷新列表
        }
      })
    },
    //删除公告
    deleteNotice(noticeId) {
      this.$confirm('确定要删除这条公告吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios({
          url: `/api/notice/${noticeId}`,
          method: 'delete',
        }).then(res => {
          this.getNotice();
          this.$message.success('删除成功');
        })
      })
    }
  }
};
</script>

<style lang="less" scoped>
.all {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .title {
    margin-bottom: 20px;
  }
}
</style>
