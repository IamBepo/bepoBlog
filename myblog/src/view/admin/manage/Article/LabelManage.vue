<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">标签管理</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <div style="background-color: white;margin: 20px 0;padding: 20px;">
        <div style="display: flex;">
            <el-button type="primary">
                <el-icon>
                    <Refresh />
                </el-icon>
            </el-button>
            <el-button @click="LabelVisible = true">
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
                <el-table-column prop="name" label="标签名" width="180" />
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

    <el-dialog v-model="LabelVisible" title="新增标签" width="400px" draggable destroy-on-close>
        <el-input v-model="labelInput" placeholder="输入新增标签名">
            <template #prepend>Name</template>  
        </el-input>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="LabelVisible = false">取消</el-button>
                <el-button type="primary" @click="addLabel">新增</el-button>
            </span>
        </template>
    </el-dialog>

    <el-dialog v-model="ExitLabelVisible" title="编辑标签" width="400px" draggable destroy-on-close @close="closeExit">
        <el-input v-model="ExitInput" :placeholder="exitObject.name">
            <template #prepend>Name</template>  
        </el-input>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="ExitLabelVisible = false">取消</el-button>
                <el-button type="primary" @click="ExitLabel">修改</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { ElMessage } from 'element-plus'
import { onMounted } from "@vue/runtime-core";
import articleApi from '../../../../api/ArticleApi'

    let searchValue = ref('')
    let LabelVisible = ref(false)
    let ExitLabelVisible = ref(false)
    let labelInput = ref('')
    let ExitInput = ref('')
    let exitObject = ref('')

    let tableData = ref([
    ])

    function searchFun(){
        let data = {
            content:searchValue.value
        }
        articleApi.getSearchTab(data).then(res => {
            tableData.value = res.data.data
        })
    }

    function switchChange(value){
        let data = {
            id:value.id,
            status:value.status
        }
        articleApi.updateTabStatus(data).then(res => {
            ElMessage({
                message: '修改标签状态成功.',
                type: 'success',
            })
        })
    }

    function addLabel(){
        let data = {
            name : labelInput.value
        }
        articleApi.addLabel(data).then(res => {
            LabelVisible.value = false
            labelInput.value = ''
            listTab()
            ElMessage({
                message: '添加标签成功.',
                type: 'success',
            })
        })
    }

    function listTab(){
        articleApi.getTabList().then(res => {
            tableData.value = res.data.data
        })
    }

    function handleEdit(value){
        ExitLabelVisible.value = true
        exitObject.value = value
    }

    function ExitLabel(){
        let data = {
            id:exitObject.value.id,
            newName:ExitInput.value
        }
        articleApi.updateTabContent(data).then(res => {
            ExitLabelVisible.value = false
            ExitInput.value = ''
            listTab()
            ElMessage({
                message: '修改标签成功.',
                type: 'success',
            })
        })
    }

    function handleDelete(row){
        articleApi.removeOnceTab(row.id).then(res => {
            listTab()
            ElMessage({
                message: '删除标签成功.',
                type: 'success',
            })
        })
    }

    function closeExit(){
        ExitInput.value = ''
        exitObject.value = ''
    }

    onMounted:{
        listTab()
    }
</script>

<style scoped>

</style>