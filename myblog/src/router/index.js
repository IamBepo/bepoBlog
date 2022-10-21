import { createRouter,createWebHistory } from "vue-router";
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
import { useStore } from "vuex";
import systemApi from "../api/SystemApi";

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
        systemApi.getAuthorizationRouter(localStorage.getItem('token')).then(res => {
            let dataRouter = res.data.data.routerList
            console.log(res.data)
            let headAdminRouter = [
                {
                path: dataRouter[0].path,
                name: dataRouter[0].name,
                component: routerComponents[dataRouter[0].component],
                mate: {
                    title: dataRouter[0].title
                },
                children: dataRouter[0].nextRouter
                }
            ]
            headAdminRouter[0].children.forEach(item => {
                item.component = routerComponents[item.component]
                item.children = item.nextRouter
                item.children.forEach(childItem => {
                    childItem.component = routerComponents[childItem.component]
                })
            })
            // store.commit('setAdminRouter',headAdminRouter)
            headAdminRouter.forEach(item => {
                router.addRoute(item)
            })
        })

        router.addRoute({
            path: '/:pathMatch(.*)',
            name:'404',
            component: routerComponents['NotFind']
        })
        next({ ...to, replace: true })
    }else{
        next()
    }

  })
export default router;