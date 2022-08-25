<template>
    <el-dialog v-model="$store.state.coverSelectVisble" title="封面管理" width="80%" top="5vh">
        <el-upload v-model:file-list="fileList" action="http://localhost:7777/api/upload/img" list-type="picture-card"
            accept="image/*" hide-on-click-modal>
            <el-icon>
                <Plus />
            </el-icon>

            <template #file="{ file }">
                <div>
                    <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                    <span class="el-upload-list__item-actions">
                        <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                            <el-icon>
                                <zoom-in />
                            </el-icon>
                        </span>
                        <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
                            <el-icon>
                                <Select />
                            </el-icon>
                        </span>
                        <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                            <el-icon>
                                <Delete />
                            </el-icon>
                        </span>
                    </span>
                </div>
            </template>
        </el-upload>

        <el-dialog v-model="dialogVisible" top="5vh">
            <img style="width: 100%;" :src="dialogImageUrl" alt="Preview Image" />
        </el-dialog>

        <el-pagination layout="prev, pager, next" :total="50" />
    </el-dialog>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { onMounted } from "@vue/runtime-core";
import { useStore } from "vuex";
import { ElMessage } from 'element-plus'
import axois from '../plugins/axios'
import store from "../store";

    const dialogImageUrl = ref([
        {
            url:'http://up.llxuo.top/autospoofingAPP/06d06506-6aba-4576-80ef-20701f5dcbde.jpg'
        }
    ])
    const dialogVisible = ref(false)
    const disabled = ref(false)

    let fileList = ref([
    ])

    const handleRemove = (file) => {
        axois({
            url:'blog/cover/remove/' + file.id,
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                ElMessage({
                    message: '删除成功.',
                    type: 'success',
                })
            }
            else{
                ElMessage({
                    message: '删除失败.',
                    type: 'error',
                })
            }
        }).catch(error => {
            ElMessage({
                message: '删除失败.',
                type: 'error',
            })
        })
    }

    const handlePictureCardPreview = (file) => {
        dialogImageUrl.value = file.url
        dialogVisible.value = true
    }

    const handleDownload = (file) => {
        store.commit('coverSelect',file)
        store.commit('coverSelectVisbleJudge',false)
    }

    function listCover(){
        axois({
            url:'blog/cover/list',
            method:'GET'
        }).then(res => {
            fileList.value = res.data.data
        })
    }

    onMounted:{
        listCover()
    }
</script>