import { createRouter,createWebHistory } from "vue-router";
import {dycRouter} from './dyc.js'
import View from '../view/View.vue'
import MajorContent from '../view/majoy/MajoyContent.vue'
import AboutBlogger from '../view/about/AboutBlogger.vue'
import Admin from '../view/Admin.vue'
import Welcome from '../view/admin/manage/Welcome.vue'
import LabelManage from '../view/admin/manage/Article/LabelManage.vue'
import ArticleManage from '../view/admin/manage/Article/ArticleManage.vue'
import ArticleMajor from '../view/admin/manage/Article/ArticleSubpageManage/Major.vue'
import ArticleAdd from '../view/admin/manage/Article/ArticleSubpageManage/AddArticle.vue'
import ClassManage from '../view/admin/manage/Article/ClassManage.vue'
import RecommendManage from '../view/admin/manage/Article/RecommendManage.vue'
import CommentManage from '../view/admin/manage/Article/CommentManage.vue'
import BasicSetting from '../view/admin/manage/system/BasicSetting.vue'
import FriendLink from '../view/admin/manage/system/FriendLink.vue'
import UserList from '../view/admin/manage/user/UserList.vue'
import VisitHistory from '../view/admin/manage/user/VisitHistory.vue'
import NotFind from '../view/404/404.vue'
import ReadModel from '../view/read/ReadModel.vue'
import qs from 'qs'
import axios from '../plugins/axios'
import { useStore } from "vuex";

const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:'/',
            name:'View',
            component:View,
            children:[
                {
                    path:'/',
                    name:'majorContent',
                    component:MajorContent
                },
                {
                    path:'/about',
                    name:'about',
                    component:AboutBlogger
                },
                {
                    path:'/readmodel',
                    name:'read',
                    component:ReadModel
                },
                {
                    path:'/read/:id',
                    name:'readId',
                    component:ReadModel
                }
            ]
        },
        // {
        //     path:'/:pathMatch(.*)',
        //     name:'404',
        //     component:NotFind
        // }
    ]
})

router.beforeEach((to, from, next) => {
    const store = useStore()
    const routerComponents = {
        'Admin':Admin,
        'Welcome':Welcome,
        'LabelManage':LabelManage,
        'ArticleManage':ArticleManage,
        'ArticleMajor':ArticleMajor,
        'ArticleAdd':ArticleAdd,
        'ClassManage':ClassManage,
        'RecommendManage':RecommendManage,
        'CommentManage':CommentManage,
        'BasicSetting':BasicSetting,
        'FriendLink':FriendLink,
        'UserList':UserList,
        'VisitHistory':VisitHistory,
        'NotFind':NotFind
    }
    if (to.meta.title) {
      document.title = to.meta.title
    }

    if (!to.matched.length) { // 当路由不存在的时候
        axios({
            url:'/user/authorization',
            method:'POST',
            data:qs.stringify({
              token:localStorage.getItem('token')
            })
        }).then(res => {
            let dataRouter = res.data.data.routerList
            console.log(res.data.data.routerList)
            let headAdminRouter = [
              {
                path: dataRouter[0].path,
                name: dataRouter[0].name,
                component: routerComponents[dataRouter[0].component],
                mate: {
                  title: '后台管理 - 首页'
                },
                children: dataRouter[0].nextRouter
              }
            ]
            headAdminRouter[0].children.forEach(item => {
                item.component = routerComponents[item.component]
            })
            // store.commit('setAdminRouter',headAdminRouter)
            headAdminRouter.forEach(item => {
                console.log(item)
                router.addRoute(item)
            })
            console.log(headAdminRouter)
            next({ ...to, replace: true })
        })
    }else{
        next()
    }

  })
export default router;