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
export const dycRouter = [{
        path:'/admin',
        name:'Admin',
        component:Admin,
        meta:{
            title:'后台管理 - 首页'
        },
        children:[
            {
                path:'/admin',
                name:'admin',
                component:Welcome,
                meta:{
                    title:'后台管理 - 首页'
                },
            },
            {
                path:'labelManage',
                name:'labelManage',
                component:LabelManage,
                meta:{
                    title:'后台管理 - 标签管理'
                },
            },
            {
                path:'articleManage',
                name:'articleManage',
                component:ArticleManage,
                redirect:'/admin/articleManage/major',
                children:[
                    {
                        path:'major',
                        name:'articleMajor',
                        component:ArticleMajor
                    },
                    {
                        path:'add',
                        name:'articleAdd',
                        component:ArticleAdd
                    }
                ]
            },
            {
                path:'classManage',
                name:'classManage',
                component:ClassManage
            },
            {
                path:'recommendManage', 
                name:'recommendManage',
                component:RecommendManage
            },
            {
                path:'commentManage',
                name:'commentManage',
                component:CommentManage
            },
            {
                path:'basicSetting',
                name:'basicSetting',
                component:BasicSetting
            },
            {
                path:'friendLink',
                name:'friendLink',
                component:FriendLink
            },
            {
                path:'userList',
                name:'userList',
                component:UserList
            },
            {
                path:'visitHistory',
                name:'visitHistory',
                component:VisitHistory
            }
        ]
    },
    {
        path:'/:pathMatch(.*)',
        name:'404',
        component:NotFind
    }
]