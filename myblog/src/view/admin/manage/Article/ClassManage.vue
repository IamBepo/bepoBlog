<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">分类管理</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <div style="background-color: white;margin: 20px 0;padding: 20px;">
        <div style="display: flex;">
            <el-button type="primary">
                <el-icon>
                    <Refresh />
                </el-icon>
            </el-button>
            <el-button @click="addSortVisible = true">
                <el-icon>
                    <Plus />
                </el-icon>新增
            </el-button>
            <div style="padding-left: 20px;">
                <el-input placeholder="Search.." v-model="searchValue" clearable prefix-icon="Search"
                    @keydown.enter.native="searchFun()">
                </el-input>
            </div>
        </div>
        <div style="margin-top: 10px;">
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="name" label="分类名" width="180" />
                <el-table-column prop="relation" label="关联" width="180">
                    <template #default="scope">
                        <el-button type="success" @click="relation(scope.$index, scope.row)">查看关联</el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="start" label="启动" width="180">
                    <template #default="{row}">
                        <el-switch v-model="row.status" class="mt-2" inline-prompt @change="switchChange(row)" :active-value=1 :inactive-value=0 />
                    </template>
                </el-table-column>
                <el-table-column prop="operation" label="操作">
                    <template #default="{row}">
                        <el-button @click="handleEdit(row)">编辑</el-button>
                        <el-button type="danger" @click="handleDelete(row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div style="margin-top: 20px;">
            <el-pagination background layout="prev, pager, next" :total="50" class="mt-4" />
        </div>
    </div>


    <el-dialog v-model="addSortVisible" title="新增分类" width="400px" draggable destroy-on-close>
        <el-input v-model="addSortInput" placeholder="输入新增分类名">
            <template #prepend>Name</template>  
        </el-input>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="addSortVisible = false">取消</el-button>
                <el-button type="primary" @click="addSort">新增</el-button>
            </span>
        </template>
    </el-dialog>

    <el-dialog v-model="ExitSortVisible" title="编辑分类" width="400px" draggable destroy-on-close @close="closeExit">
        <el-input v-model="ExitInput" :placeholder="exitObject.name">
            <template #prepend>Name</template>  
        </el-input>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="ExitSortVisible = false">取消</el-button>
                <el-button type="primary" @click="ExitSort">修改</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import axois from '../../../../plugins/axios'
import qs from 'qs'
import { ElMessage } from 'element-plus'
import { onMounted } from "@vue/runtime-core";

    let searchValue = ref('')
    let addSortVisible = ref(false)
    let addSortInput = ref('')

    let ExitSortVisible = ref(false)
    let ExitInput = ref('')
    let exitObject = ref('')

    const tableData = ref([])

    function searchFun(){
        let data = {
            content:searchValue.value
        }
        axois({
            url:'/blog/sort/search',
            method:'POST',
            data:qs.stringify(data)
        }).then(res=>{
            if(res.data.code === '200'){
                tableData.value = res.data.data
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
                type: 'error'
            })
        })
    }

    function switchChange(value){
        let data = {
            id:value.id,
            status:value.status
        }
        axois({
            url:'/blog/sort/update/status',
            method:'POST',
            data:qs.stringify(data)
        }).then(res => {
            if(res.data.code === '200'){
                ElMessage({
                    message: '修改分类状态成功.',
                    type: 'success',
                })
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

    function addSort(){
        let data = {
            name:addSortInput.value
        }
        axois({
            url:'/blog/sort/save',
            method:'POST',
            data:qs.stringify(data)
        }).then(res => {
            if(res.data.code === '200'){
                addSortVisible.value = false
                addSortInput.value = ''
                listSort()
                ElMessage({
                    message: '新增分类成功.',
                    type: 'success',
                })
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

    function ExitSort(){
        let data = {
            id:exitObject.value.id,
            newName:ExitInput.value
        }
        axois({
            url:'/blog/sort/update/name',
            method:'post',
            data:qs.stringify(data)
        }).then(res => {
            if(res.data.code === '200'){
                ExitSortVisible.value = false
                ExitInput.value = ''
                listSort()
                ElMessage({
                    message: '修改分类成功.',
                    type: 'success',
                })
            }
            else{
                ElMessage({
                    message: '修改分类失败.',
                    type: 'error',
                })
            }
        }).catch(error => {
            console.log(error)
            ElMessage({
                message: '修改分类失败.',
                type: 'error',
            })
        })
    }

    function closeExit(){
        ExitInput.value = ''
        exitObject.value = ''
    }

    function handleEdit(row){
        ExitSortVisible.value = true
        exitObject.value = row
    }

    function handleDelete(row){
        axois({
            url:'blog/sort/remove/' + row.id,
            method:'POST',
        }).then(res => {
            if(res.data.code === '200'){
                listSort()
                ElMessage({
                    message: '删除分类成功.',
                    type: 'success',
                })
            }
            else{
                ElMessage({
                    message: '删除分类失败.',
                    type: 'error',
                })
            }
        }).catch(error => {
            console.log(error)
            ElMessage({
                message: '删除分类失败.',
                type: 'error',
            })
        })
    }

    function listSort(){
        axois({
            url:'/blog/sort/list',
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                tableData.value = res.data.data
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
        listSort()
    }
</script>

<style scoped>

</style>