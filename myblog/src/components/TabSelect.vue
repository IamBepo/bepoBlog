<template>
    <el-dialog v-model="$store.state.tabSelectVisble" title="标签选择" width="20%">
        <el-scrollbar height="400px">
            <div v-for="item in $store.state.tabSelected">
                <el-checkbox v-model="item.selected" :label="item.name" size="large"/>
            </div>
        </el-scrollbar>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="$store.commit('tabSelectVisbleJudge',false)">关闭</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref } from "@vue/reactivity"
import { onMounted } from "@vue/runtime-core"
import axois from '../plugins/axios'
import store from "../store";

    function listTab(){
        axois({
            url:'/blog/tab/list',
            method:'GET'
        }).then(res=>{
            if(res.data.code === '200'){
                store.commit('tabSelected',res.data.data)
            }
            else{
                ElMessage({
                    message: '网络似乎出现了问题.',
                    type: 'error',
                })
            }
        }).catch(error =>{
            ElMessage({
                message: '网络似乎出现了问题.',
                type: 'error',
            })
        })
    }

    onMounted:{
        listTab()
    }
</script>