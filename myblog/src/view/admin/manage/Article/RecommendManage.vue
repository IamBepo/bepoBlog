<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">推荐管理</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <div style="background-color: white;margin: 20px 0;padding: 20px;">

        <div>
            <div>
                <div style="display: flex;">
                    <el-button type="primary">
                        <el-icon>
                            <Refresh />
                        </el-icon>
                    </el-button>
                    <el-button @click="addListRecommend">
                        <el-icon>
                            <Plus />
                        </el-icon>新增列表推荐
                    </el-button>
                </div>
                <el-table :data="listRecommend" stripe style="width: 100%">
                    <el-table-column prop="title" label="推荐文章" width="700" />
                    <el-table-column prop="power" label="权重" width="100" />
                    <el-table-column prop="operation" label="操作">
                        <template #default="{row}">
                            <el-button @click="handleEdit(row)">更改权重</el-button>
                            <el-button type="danger" @click="handleDelete(row,'LIST')">删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>

        <div style="margin-top: 50px;">
            <div>
                <div style="display: flex;">
                    <el-button type="primary">
                        <el-icon>
                            <Refresh />
                        </el-icon>
                    </el-button>
                    <el-button @click="addWalkRecommend">
                        <el-icon>
                            <Plus />
                        </el-icon>新增走马灯推荐
                    </el-button>
                </div>
                <el-table :data="walkRecommend" stripe style="width: 100%">
                    <el-table-column prop="title" label="推荐文章" width="700" />
                    <el-table-column prop="power" label="权重" width="100" />
                    <el-table-column prop="operation" label="操作">
                        <template #default="{row}">
                            <el-button @click="handleEdit(row)">更改权重</el-button>
                            <el-button type="danger" @click="handleDelete(row,'WALK')">删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
    </div>

    <el-dialog v-model="recommendSelectVisble" title="推荐选择" width="50%">
        <el-scrollbar height="400px">
            <el-radio-group v-model="selectFirstId">
                <div v-for="item in selectList" style="margin-bottom: 5px;">
                    <el-radio :label="item.id" size="large" border>{{item.title}}</el-radio>
                    <br />
                </div>
            </el-radio-group>
        </el-scrollbar>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="addListRecommendFun(addType)">新增</el-button>
                <el-button @click="recommendSelectVisble = false">关闭</el-button>
            </span>
        </template>
    </el-dialog>

    <el-dialog v-model="powerVisible" title="更改权重" width="400px" draggable destroy-on-close @close="closeExit">
        <el-input type="number" v-model="exitInput" :placeholder="'' + power">
            <template #prepend>权重</template>  
        </el-input>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="powerVisible = false">取消</el-button>
                <el-button type="primary" @click="exitPower">修改</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { inject, onMounted } from "@vue/runtime-core";
import { ElMessage } from 'element-plus'

    const qs = inject('$qs')
    const axios = inject('$axios')
    let recommendSelectVisble = ref(false)
    let powerVisible = ref(false)
    let exitInput = ref()
    let power = ref()
    let exitPowerId = ref()
    let selectList = ref()
    let selectFirstId = ref()
    let listRecommend = ref()
    let walkRecommend = ref()
    let addType = ref()

    function listNoListRecommend(){
        axios({
            url:'/recommend/list/no/list',
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                selectList.value = res.data.data
            }
        })
    }

    function addListRecommend(){
        recommendSelectVisble.value = true
        addType.value = 'LIST'
        listNoListRecommend()
    }

    function addListRecommendFun(type){
        recommendSelectVisble.value = false
        
        axios({
            url:type==='LIST'?'/recommend/save/list/' + selectFirstId.value:'/recommend/save/walk/'+ selectFirstId.value,
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                ElMessage({
                    message: '新增推荐成功.',
                    type: 'success',
                })
            }else{
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

    function listListRecommend(){
        axios({
            url:'/recommend/list/all/list',
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                listRecommend.value = res.data.data
            }else{
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

    function listWalkRecommend(){
        axios({
            url:'/recommend/list/all/walk',
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                walkRecommend.value = res.data.data
            }else{
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

    function handleEdit(row){
        powerVisible.value = true
        power.value = row.power
        exitPowerId.value = row.recommendId
    }

    function closeExit(){
        exitInput.value = ''
    }

    function exitPower(){
        axios({
            url:'/recommend/update/power',
            method:'POST',
            data:qs.stringify({
                recommentId:exitPowerId.value,
                power:exitInput.value
            })
        }).then(res => {
            exitInput.value = ''
            if(res.data.code === '200'){
                ElMessage({
                    message: '修改权重成功.',
                    type: 'success',
                })
            }else{
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
        powerVisible.value = false
    }

    function handleDelete(row,type){
        axios({
            url:'/recommend/remove',
            method:'POST',
            data:qs.stringify({
                id:row.id,
                recommentId:row.recommendId,
                type:type
            })
        }).then(res => {
            if(res.data.code === '200'){
                ElMessage({
                    message: '删除成功.',
                    type: 'success',
                })
            }else{
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

    function listNoWalkRecommend(){
        axios({
            url:'/recommend/list/no/walk',
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                selectList.value = res.data.data
            }
        })
    }

    function addWalkRecommend(){
        recommendSelectVisble.value = true
        addType.value = 'WALK'
        listNoWalkRecommend()
    }

    onMounted:{
        listListRecommend()
        listWalkRecommend()
    }
</script>

<style scoped>
    .el-radio-group {
        display: block;
    }
    .el-radio.is-bordered.el-radio--large {
        width: 100%;
    }
</style>