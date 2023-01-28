<template>
    <div class="container">
        <div class="lContainer">
            <div class="imgContainer">
                <img :src="$store.state.systemInfo.webLogo" />
            </div>
            <div style="padding-left: 10px;font-size: 20px;">
                {{$store.state.systemInfo.navTitle}}
            </div>
            <div style="padding-left: 50px;display: flex;align-items: center;height: 100%;">
                <el-menu :default-active="activedMenu" class="el-menu-demo" mode="horizontal" @select="handleSelect"
                    :ellipsis="false" active-text-color="#6b6adf">
                    <el-menu-item index="majorContent" style="font-size: 16px;">
                        <el-icon>
                            <HotWater />
                        </el-icon>
                        首页
                    </el-menu-item>
                    <el-sub-menu index="classify">
                        <template #title>
                            <div style="font-size: 16px;">
                                <el-icon>
                                    <Files />
                                </el-icon>
                                分类
                            </div>
                        </template>
                        <el-menu-item v-for="item in classify" :key="item.name" :index="item.name">{{item.label}}
                        </el-menu-item>
                    </el-sub-menu>
                    <el-menu-item index="about" style="font-size: 16px;">
                        <el-icon>
                            <Chicken />
                        </el-icon>
                        博主
                    </el-menu-item>
                </el-menu>
            </div>
        </div>
        <div class="rContainer" >
            <div>
                <el-input placeholder="Search.." v-model="searchValue" clearable prefix-icon="Search"
                    @keydown.enter.native="searchFun()">
                </el-input>
            </div>
            <div style="margin-left: 15px;" v-show="!$store.state.isLogin">
                <el-link type="primary" @click="$store.commit('loginVisbleJudge',true)">登录/注册</el-link>
            </div>
            <div style="margin-left: 15px;" v-show="$store.state.isLogin">
                <el-dropdown>
                    <img style="height: 40px;width: 40px;border-radius: 50%;cursor: pointer;" src="../assets/Avatar.jpg"/>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item>消息 (0)</el-dropdown-item>
                            <el-dropdown-item>个人中心</el-dropdown-item>
                            <el-dropdown-item @click="logOut">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </div>
    </div>

    <Login></Login>
</template>

<script>
import { computed, onMounted, reactive, ref } from 'vue';
import {useRoute,useRouter} from 'vue-router'
import Login from './Login.vue'
import { ElMessage } from 'element-plus'
import { useStore } from 'vuex';

export default {
    name:'topNav',
    components: {
        Login
    },
    setup(){

        let route = useRoute()
        let router = useRouter()
        let store = useStore()

        let searchValue = ref('')

        let classify = reactive([

            {
                label: 'JAVA',
                name: 'java'
            },
            {
                label: '框架',
                name: 'frame'
            }
        ])

        let activedMenu = computed(()=>{
            if (route.path.indexOf('/about') == 0) {
                return 'about'
            } else if (route.path.indexOf('/') == 0) {
                return 'majorContent'
            }
            return ''
        })


        function searchFun() {
            if(searchValue.value) {
                router.push({
                    name:'search',
                    query:{
                        keyWord:searchValue.value
                    }
                })
                searchValue.value = ""
            }else {
                ElMessage({
                    message: '未输入搜索内容.',
                    type: 'error',
                })
            }
        }

        function logOut() {
            localStorage.removeItem('token')
            store.commit('loginJudge',false)
            ElMessage({
                message: '退出登录成功.',
                type: 'success',
            })
        }

        const handleSelect = (key, keyPath) => {
            router.push({
                name:key
            })
        }

        return {
            searchValue,
            searchFun,
            classify,
            activedMenu,
            handleSelect,
            logOut
        }
    }
}
</script>

<style scoped>
.container {
        display: flex;
        align-items: center;
        width: 100%;
        height: 60px;
        box-shadow: 0 0 10px 0 rgba(0, 0, 0, .2);
        position: relative;
        justify-content: space-between;

    }
    .imgContainer {
        height: 36px;
    }
    .imgContainer img {
        width: 36px;
    }
    .lContainer {
        padding-left: 20px;
        display: flex;
        align-items: center;
        height: 100%;
    }
    .rContainer {
        padding-right: 20px;
        display: flex;
        align-items: center;
    }
    .el-submenu__title:hover,.el-menu-item:hover,::v-deep .el-submenu .el-submenu__title:hover{
        background-color: rgba(194, 229, 202, 0.32) !important; 
    }
    ::v-deep .el-input__wrapper {
        border-radius: 30px;
    }
    .el-input {
        width: 110px;
        transition: all .3s linear;
    }
    .el-input:hover {
        width: 200px;
        -webkit-transition: all .5s ease-out;
        transition: all .3s ease-out;
    }
    .el-link {
        font-size: 15px;
    }

    .example-showcase .el-dropdown+.el-dropdown {
        margin-left: 15px;
    }

    .example-showcase .el-dropdown-link {
        cursor: pointer;
        color: var(--el-color-primary);
        display: flex;
        align-items: center;
    }
</style>