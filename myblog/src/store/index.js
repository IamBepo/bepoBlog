import { createStore } from 'vuex'

export default createStore({
  state: {
    loginVisble:false,
    isLogin:false,
    isCollapse:false,
    coverSelectVisble:false,
    coverUrl:'',
    tabSelectVisble:false,
    tabSelected:'',
    systemInfo:{
      webTitle:'',
      webLogo:'',
      navTitle:'',
      aboutBlogger:'',
      underIntroduction:'',
      underPoweredBy:'',
      underCopyright:'',
      underIcp:''
    },
    adminRouter:[{
      path:'/admin',
      name:'Admin',
      compoent:'',
      mate:{
        title:'后台管理 - 首页'
      },
      children:[{}]
    }]
  },
  mutations: {
    loginVisbleJudge(state,value){
      state.loginVisble = value
    },
    loginJudge(state,value){
      state.isLogin = value
    },
    adminNav(state,value){
      state.isCollapse = value
    },
    coverSelectVisbleJudge(state,value){
      state.coverSelectVisble = value
    },
    coverSelect(state,value){
      state.coverUrl = value
    },
    tabSelectVisbleJudge(state,value){
      state.tabSelectVisble = value
    },
    tabSelected(state,value){
      state.tabSelected = value
    },
    tabRemove(state,value){
      for(let i=0;i<state.tabSelected.length;i++){
        if(state.tabSelected[i].id === value.id){
          state.tabSelected[i].selected = false
        }
      }
    },
    getSystemInfo(state,value){
      state.systemInfo = value
    },
    setAdminRouter(state,value){
      state.adminRouter = value
    },
  },
  actions: {

  },
})