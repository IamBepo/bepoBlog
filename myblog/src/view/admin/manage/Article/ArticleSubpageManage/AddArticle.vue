<template>
    <div>
        <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/admin' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/admin/articleManage/major' }">文章管理</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">发表文章</a></el-breadcrumb-item>
        </el-breadcrumb>
    </div>

    <div style="padding-top: 20px;">
        <div style="width: 500px;">
            <el-input v-model="titleInput" placeholder="文章标题" size="large">
                <template #prepend>标题 *</template>
            </el-input>
        </div>
    </div>

    <div style="padding-top: 20px;">
        <div v-show="$store.state.coverUrl === ''" class="cover" @click="$store.commit('coverSelectVisbleJudge',true)">
            封面
        </div>
        <div v-show="$store.state.coverUrl !== ''">
            <el-image class="cover" style="width: 500px; height: 300px" :src="$store.state.coverUrl.url" fit="fill" @click="$store.commit('coverSelectVisbleJudge',true)"/>
        </div>
    </div>

    <div style="padding-top: 10px;">
        <el-tag v-for="tag in $store.state.tabSelected" :key="tag.id" v-show="tag.selected" class="mx-1" closable size="large" style="margin-right: 5px;"
            @close="handleCloseTag(tag)">
            {{ tag.name }}
        </el-tag>
        <el-button class="button-new-tag ml-1" @click="$store.commit('tabSelectVisbleJudge',true)">
            + 添加标签
        </el-button>
    </div>

    <div style="padding-top: 10px;display: flex;justify-content: space-between;">
        <div>
            <el-select v-model="sortValue" class="m-2" placeholder="分类选择" size="large">
                <el-option v-for="item in sortList" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
        </div>
        <div>
            <el-button class="button-new-tag ml-1" @click="publicArticle" size="large" style="width: 150px;"  type="primary">
                发表
            </el-button>
        </div>
    </div>

    <div style="padding-top: 10px;">
        <v-md-editor v-model="content" height="900px" toc-nav-position-right :disabled-menus="[]"
            @upload-image="handleUploadImage"
            left-toolbar="undo redo clear | h bold italic strikethrough quote | ul ol table hr | link image code tip emoji | save"
            @copy-code-success="handleCopyCodeSuccess"></v-md-editor>
    </div>

    <CoverSelect></CoverSelect>
    <TabSelect></TabSelect>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { onMounted } from "@vue/runtime-core";
import { ElMessage ,ElMessageBox } from 'element-plus'
import CoverSelect from '../../../../../components/CoverSelect.vue'
import TabSelect from '../../../../../components/TabSelect.vue'
import store from "../../../../../store"
import axois from '../../../../../plugins/axios'
import qs from 'qs'

    let content = ref('')
    let titleInput = ref('')

    function handleUploadImage(event, insertImage, files) {
      // 拿到 files 之后上传到文件服务器，然后向编辑框中插入对应的内容
      console.log(files);

      // 此处只做示例
      insertImage({
        url:
          'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp09%2F210F2130512J47-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663139474&t=e96a65daea251d9fd83ca7abedbba81d',
        desc: '七龙珠',
        // width: 'auto',
        // height: 'auto',
      });
    }

    function handleCopyCodeSuccess(){
        ElMessage({
            message: '复制成功.',
            type: 'success',
        })
    }

    function handleCloseTag(tag){
        store.commit('tabRemove',tag)
    }

    let sortValue = ref('')

    let sortList = ref('')

    function listSort(){
        axois({
            url:'/blog/sort/list',
            method:'GET'
        }).then(res => {
            if(res.data.code === '200'){
                sortList.value = res.data.data
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

    function publicArticle(){
        let exist = false
        let errorMsg = ''
        if(titleInput.value === ''){
            exist = true
            errorMsg += '<div>标题未填写</div>'
        }
        if(store.state.coverUrl === ''){
            exist = true
            errorMsg += '<div>封面未选择</div>'
        }
        if(sortValue.value === ''){
            exist = true
            errorMsg += '<div>分类未选择</div>'
        }
        if(exist === true){
            ElMessageBox.alert(errorMsg, '缺少必要信息', {
                confirmButtonText: '确认',
                dangerouslyUseHTMLString: true,
            })
        }else{
            let tags = ''
            for(let item of store.state.tabSelected){
                if(item.selected === true){
                    if(tags === '') tags += item.id
                    else tags += ',' + item.id
                }
            }
            let data = {
                title:titleInput.value,
                coverId:store.state.coverUrl.id,
                content:content.value,
                sortId:sortValue.value,
                tabId: tags,
            }
            axois({
                url:'/blog/save',
                method:'POST',
                data: qs.stringify(data)
            }).then(res => {
                if(res.data.code === '200'){
                    titleInput.value = '',
                    store.commit('coverSelect','')
                    content.value = ''
                    ElMessage({
                        message: '发表成功.',
                        type: 'success',
                    })
                }else{
                    ElMessage({
                        message: '网络似乎出现了问题.',
                        type: 'error',
                    })
                }
            }).catch(error => {
                ElMessage({
                    message: '网络似乎出现了问题.',
                    type: 'error',
                })
            })
        }

    }

    onMounted:{
        listSort()
    }
</script>

<style scoped>
    .cover {
        width: 500px;
        height: 300px;
        background-color: #FAFAFA;
        border: #c5b7b7 1px dashed;
        border-radius: 3px;
        text-align: center;
        line-height: 300px;
        color: #c5b7b7;
        font-size: 20px;
    }
    .cover:hover{
        border: #86aee2 1px dashed;
        cursor: pointer;
    }
</style>