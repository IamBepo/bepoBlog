<template>
    <!-- <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
        <el-radio-button :label="false">expand</el-radio-button>
        <el-radio-button :label="true">collapse</el-radio-button>
    </el-radio-group> -->
    <el-menu :default-active="activedMenu" class="el-menu-vertical-demo" :collapse="$store.state.isCollapse" @select="handleSelect">
        <div style="text-align: center;padding: 20px 0;" v-show="!$store.state.isCollapse">
            <img style="width: 70px;height: 70px;border-radius: 50%;" src="../../assets/Avatar.jpg" />
        </div>

        <el-menu-item index="admin">
            <el-icon>
                <House />
            </el-icon>
            <template #title>首页</template>
        </el-menu-item>
        <el-sub-menu v-for="item in articleList" :index="item.name">
            <template #title>
                <el-icon><DataBoard /></el-icon>
                <span>{{item.label}}</span>
            </template>
            <el-menu-item-group style="background-color: #76797c;" v-for="cItem in item.cList">
                <el-menu-item :index="cItem.name">{{cItem.label}}</el-menu-item>
            </el-menu-item-group>
        </el-sub-menu>

        <el-sub-menu v-for="item in userList" :index="item.name">
            <template #title>
                <el-icon><DataBoard /></el-icon>
                <span>{{item.label}}</span>
            </template>
            <el-menu-item-group style="background-color: #76797c;" v-for="cItem in item.cList">
                <el-menu-item :index="cItem.name">{{cItem.label}}</el-menu-item>
            </el-menu-item-group>
        </el-sub-menu>

        <el-sub-menu v-for="item in systemList" :index="item.name">
            <template #title>
                <el-icon><DataBoard /></el-icon>
                <span>{{item.label}}</span>
            </template>
            <el-menu-item-group style="background-color: #76797c;" v-for="cItem in item.cList">
                <el-menu-item :index="cItem.name">{{cItem.label}}</el-menu-item>
            </el-menu-item-group>
        </el-sub-menu>
    </el-menu>
</template>

<script setup>
import { reactive, ref ,computed} from "@vue/reactivity"
import {useRoute,useRouter} from 'vue-router'

    let route = useRoute()
    let router = useRouter()

    const articleList = reactive([
        {
            label:'文章管理',
            name:'article',
            cList:[
                {
                    label:'文章管理',
                    name:'articleManage'
                },
                {
                    label:'标签管理',
                    name:'labelManage'
                },
                {
                    label:'分类管理',
                    name:'classManage'
                },
                {
                    label:'推荐管理',
                    name:'recommendManage'
                },
                {
                    label:'评论管理',
                    name:'commentManage'
                }
            ]
        }
    ])

    const systemList = reactive([
        {
            label:'系统设置',
            name:'system',
            cList:[
                {
                    label:'基本设置',
                    name:'basicSetting'
                }
            ]
        }
    ])

    const userList = reactive([
        {
            label:'用户管理',
            name:'user',
            cList:[
                {
                    label:'用户列表',
                    name:'userList'
                },
                {
                    label:'访问记录',
                    name:'visitHistory'
                }
            ]
        }
    ])

    const handleSelect = (key, keyPath) => {
        router.push({
            name:key
        })
    }

    let activedMenu = computed(()=>{
        const strArr = route.path.split('/', 3)
        if(!strArr[2]) return strArr[1]
        return strArr[2]
    })


</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 250px;
    min-height: 100vh;
    height: 100%;
    background-color: #76797c;
}
::v-deep .el-menu {
    background-color: #76797c !important;
}
.el-menu {
    background-color: #76797c !important;
    min-height: 100vh;
}
.el-menu-item{
    color: white !important;
}
.el-menu-item:hover{
    background-color: #cf7474 !important;
}
::v-deep .el-sub-menu__title {
    color: white !important;
}
::v-deep .el-sub-menu__title:hover {
    background-color: #cf7474 !important;
}
::v-deep li.el-menu-item.is-active {
    background-color: #d34d4d;
}
</style>