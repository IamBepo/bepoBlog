<template>
    <div>
        <el-dialog v-model="$store.state.loginVisble" title="LOGIN" width="20%">
            <div style="display: flex;align-items: center;justify-content: center;">
                <div>
                    <el-link type="primary" @click="showSignIn" style="font-size: 20px;">登录
                    </el-link>
                </div>
                <el-divider direction="vertical" />
                <div>
                    <el-link type="primary" @click="showSignUp" style="font-size: 20px;">注册</el-link>
                </div>
            </div>

            <div v-show="showIn" style="margin: 20px;padding: 0 10px;">
                <div style="padding-bottom: 10px;">
                    <el-input v-model="username" placeholder="请输入用户名" clearable size="large">
                        <template #prepend>
                            <el-icon style="font-size: 20px;">
                                <User />
                            </el-icon>
                        </template>
                    </el-input>
                </div>
                <div style="padding-bottom: 10px;">
                    <el-input v-model="password" placeholder="请输入密码" clearable size="large" show-password>
                        <template #prepend>
                            <el-icon style="font-size: 20px;">
                                <Key />
                            </el-icon>
                        </template>
                    </el-input>
                </div>
                <div style="display: flex;align-items: center;">
                    <div style="font-size: 16px;color: #4f6262;padding-right: 5px;">自动登录</div>
                    <el-switch v-model="autoLogin" />
                </div>
                <div style="padding-top: 10px;text-align: center;">
                    <el-button type="primary" style="width: 100%;" size="large" @click="signIn">登录</el-button>
                </div>
            </div>

            <div v-show="!showIn" style="margin: 20px;padding: 0 10px;">
                <div style="padding-bottom: 10px;">
                    <el-input v-model="username_up" placeholder="请输入用户名" clearable size="large">
                        <template #prepend>
                            <el-icon style="font-size: 20px;">
                                <User />
                            </el-icon>
                        </template>
                    </el-input>
                </div>
                <div style="padding-bottom: 10px;">
                    <el-input v-model="password_up" placeholder="请输入密码" clearable size="large" show-password>
                        <template #prepend>
                            <el-icon style="font-size: 20px;">
                                <Key />
                            </el-icon>
                        </template>
                    </el-input>
                </div>
                <div style="padding-bottom: 10px;">
                    <el-input v-model="email" placeholder="请输入邮箱" clearable size="large">
                        <template #prepend>
                            <el-icon style="font-size: 20px;">
                                <Message />
                            </el-icon>
                        </template>
                    </el-input>
                </div>
                <div style="padding-top: 10px;text-align: center;">
                    <el-button type="primary" style="width: 100%;" size="large" @click="signUp">注册</el-button>
                </div>
            </div>

        </el-dialog>
    </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { useStore } from "vuex";
import axois from '../plugins/axios'
import qs from 'qs'

    const store = useStore()

    let showIn = ref(true)
    let username = ref('')
    let password = ref('')
    let username_up = ref('')
    let password_up = ref('')
    let email = ref('')
    let autoLogin = ref(false)

    function showSignUp(){
        showIn.value = false
    }
    function showSignIn(){
        showIn.value = true
    }

    function signIn(){
        let data = {
                "userid":username.value,
                "password":password.value,
        }
        axois({
            url:'/user/signIn',
            method:'post',
            data:qs.stringify(data),
        }).then((res)=>{
            console.log(res.data)
            console.log(localStorage.getItem('token'))
            if(res.data.code === "200"){
                localStorage.setItem('token',res.data.data.token)
                store.commit('loginJudge',true)
            } 
            else{
                localStorage.removeItem('token')
                store.commit('loginJudge',false)
            } 
            
            username.value = ''
            password.value = ''
            autoLogin.value = false
        }).catch((error)=>{
            console.log(error)
        })
        store.commit('loginVisbleJudge',false)
    }

    function signUp(){
        let data = {
                "username":username_up.value,
                "password":password_up.value,
                "email":email.value
        }
        axois({
            url:'/user/signUp',
            method:'post',
            data:qs.stringify(data),
        }).then((res)=>{
            console.log(res.data)
            username_up.value = ''
            password_up.value = ''
            email.value = ''
        }).catch((error)=>{
            console.log(error)
        })
        store.commit('loginVisbleJudge',false)
    }

</script>

<style scoped>
    ::v-deep .el-dialog__body {
        padding-top:0;
        padding-bottom: 10px;
    }
</style>