import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login', //登录界面
      component: () => import('@/components/common/login.vue') // 加上了 .vue
    },
    {
      path: '/index', //教师主页
      component: () => import('@/components/admin/index.vue'), // 加上了 .vue
      children: [
        {
          path: '/noticeManage', //公告管理
          component: () => import('@/components/admin/noticeManage.vue') // 加上了 .vue
        },
        {
          path: '/aiChat', //AI智能助手
          component: () => import('@/components/common/aiChat.vue') // 加上了 .vue
        },
        {
          path: '/', //首页默认路由
          component: () => import('@/components/common/hello.vue') // 加上了 .vue
        },
        {
          path:'/grade', //学生成绩
          component: () => import('@/components/charts/grade.vue') // 加上了 .vue
        },
        {
          path: '/selectExamToPart', //学生分数段
          component: () => import('@/components/teacher/selectExamToPart.vue') // 加上了 .vue
        },
        {
          path: '/scorePart',
          component: () => import('@/components/charts/scorePart.vue') // 加上了 .vue
        },
        {
          path: '/allStudentsGrade', //所有学生成绩统计
          component: () => import('@/components/teacher/allStudentsGrade.vue') // 加上了 .vue
        },
        {
          path: '/examDescription', //考试管理功能描述
          component: () => import('@/components/teacher/examDescription.vue') // 加上了 .vue
        },
        {
          path: '/selectExam', //查询所有考试
          component: () => import('@/components/teacher/selectExam.vue') // 加上了 .vue
        },
        {
          path: '/addExam', //添加考试
          component: () => import('@/components/teacher/addExam.vue') // 加上了 .vue
        },
        {
          path: '/answerDescription', //题库管理功能介绍
          component: ()=> import('@/components/teacher/answerDescription.vue') // 加上了 .vue
        },
        {
          path: '/selectAnswer', //查询所有题库
          component: () => import('@/components/teacher/selectAnswer.vue') // 加上了 .vue
        },
        {
          path: '/addAnswer', //增加题库主界面
          component: () => import('@/components/teacher/addAnswer.vue') // 加上了 .vue
        },
        {
          path: '/addAnswerChildren', //点击试卷跳转到添加题库页面
          component: () => import('@/components/teacher/addAnswerChildren.vue') // 加上了 .vue
        },
        {
          path: '/studentManage', //学生管理界面
          component: () => import('@/components/teacher/studentManage.vue') // 加上了 .vue
        },
        {
          path: '/addStudent', //添加学生
          component: () => import('@/components/teacher/addStudent.vue') // 加上了 .vue
        },
        {
          path: '/teacherManage',
          component: () => import('@/components/admin/tacherManage.vue') // 加上了 .vue
        },
        {
          path: '/addTeacher',
          component: () => import ('@/components/admin/addTeacher.vue') // 加上了 .vue
        }
      ]
    },
    {
      path: '/student',
      component: () => import('@/components/student/index.vue'), // 加上了 .vue
      children: [
        {path:"/",component: ()=> import('@/components/student/myExam.vue')}, // 加上了 .vue
        {path:'/startExam', component: () => import('@/components/student/startExam.vue')}, // 加上了 .vue
        {path: '/manager', component: () => import('@/components/student/manager.vue')}, // 加上了 .vue
        {path: '/examMsg', component: () => import('@/components/student/examMsg.vue')}, // 加上了 .vue
        {path: '/message', component: () => import('@/components/student/message.vue')}, // 加上了 .vue
        {path: '/studentScore', component: () => import("@/components/student/answerScore.vue")}, // 加上了 .vue
        {path: '/scoreTable', component: () => import("@/components/student/scoreTable.vue")} // 加上了 .vue
      ]
    },
    {path: '/answer',component: () => import('@/components/student/answer.vue')} // 加上了 .vue
  ]
})
