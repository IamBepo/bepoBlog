<template>
    <div style="display: flex;justify-content: center;margin: 10px 0;">
        <div style="background-color: white;width: 1000px;border-radius: 10px;padding: 20px;">
            <div style="padding: 0 32px;padding-bottom: 20px;">170 评论</div>
            <div style="padding: 0 32px;display: flex;align-items:center;">
                <el-avatar :size="50" :src="avatarUrl" />
                <div style="padding-left: 20px;display: flex;align-items: center;width: 100%;">
                    <el-input v-model="replyContent" show-word-limit maxlength="250" type="textarea"
                        placeholder="文明和谐" />
                    <el-button style="margin-left: 10px;height: 90px;" type="primary" @click="firstComment(articleId)">评论</el-button>
                </div>
            </div>

            <div style="display: flex;padding: 20px 32px 0 32px;" v-for="(Fitem,index) in list" :key="Fitem.id">
                <div>
                    <el-avatar :size="50" :src="Fitem.userInfo.avatar" />
                </div>
                <div style="padding-left: 20px;width: 100%;">
                    <div style="color: #FB7299;font-size: 14px;padding-bottom: 5px;font-weight: 550;">{{Fitem.userInfo.name}}</div>
                    <div style="font-size: 15px;padding-bottom: 5px;">
                        {{Fitem.content}}
                    </div>
                    <div style="color: #999;font-size: 12px;">
                        {{Fitem.updateTime}}
                        <label class="replyBtn" @click="replyFun(Fitem.id,Fitem.userInfo.id,Fitem.userInfo.name,2,index)">回复</label>
                    </div>

                    <div style="display: flex;padding-top: 20px;" v-for="Sitem in Fitem.secondComment">
                        <div>
                            <el-avatar :size="30" :src="Sitem.userInfo.avatar" />
                        </div>
                        <div style="padding-left: 20px;">
                            <div style="color: #FB7299;font-size: 14px;padding-bottom: 5px;font-weight: 550;">
                                {{Sitem.userInfo.name}}
                                <label style="color: black;font-weight: 500;" v-if="Sitem.type === 2">：{{Sitem.content}}</label>
                                <label style="color: black;font-weight: 500;" v-if="Sitem.type === 3">
                                    <label style="padding-left: 2px;">回复</label><label style="color: blue;padding-left: 3px;">{{Sitem.replyUserInfo.name}}</label> ：{{Sitem.content}}
                                </label>
                            </div>
                            <div style="color: #999;font-size: 12px;">
                                {{Sitem.updateTime}}
                                <label class="replyBtn" @click="replyFun(Fitem.id,Sitem.userInfo.id,Sitem.userInfo.name,3,index)">回复</label>
                            </div>
                        </div>
                    </div>

                    <div style="display: flex;align-items:center;padding-top: 20px;" v-show="Fitem.replyInputVisble">
                        <el-avatar :size="50" :src="avatarUrl" />
                        <div style="padding-left: 20px;display: flex;align-items: center;width: 100%;">
                            <el-input v-model="childReplyContent" show-word-limit maxlength="250" type="textarea"
                                :placeholder="'回复 @' + childReply.replyUserName" />
                            <el-button style="margin-left: 10px;height: 90px;" type="primary" @click="childComment">评论</el-button>
                        </div>
                    </div>
                    <el-divider />
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "@vue/reactivity"
import { onMounted } from "@vue/runtime-core"
import { ElMessage } from 'element-plus'
import articleApi from '../api/ArticleApi'
    const props = defineProps({
        articleId:Number,
        list:Object
    })

    let avatarUrl = ref('http://up.llxuo.top/myblog/userAvatar/Avatar.jpg')
    let replyContent = ref()
    let childReplyContent = ref()
    let childReply = ref({
        replyInputVisble:false,
        replyUserName:'',
        type:0,
        replyId:0,
        replyUserId:0
    })

    function firstComment(id){
        let data = {
            articleId:id,
            userId:5,
            content:replyContent.value
        }
        articleApi.addFatherComment(data).then(res => {
            replyContent.value = ''
            ElMessage({
                message: '评论成功.',
                type: 'success',
            })
        })
    }

    function replyFun(replyId,replyUserId,UserName,type,index){
        if(props.list[index].replyInputVisble === false){
            props.list[index].replyInputVisble = true
            childReply.value = {
                replyInputVisble:true,
                replyUserName:UserName,
                type:type,
                replyId: replyId,
                replyUserId: replyUserId,
                index:index
            }
        }
        else {
            props.list[index].replyInputVisble = false
        }
    }

    function childComment(){
        let data = {
            firstId: childReply.value.replyId,
            userId: 5,
            replyUserId: childReply.value.replyUserId,
            content: childReplyContent.value,
            type:childReply.value.type
        }
        articleApi.addChildrenComment(data).then(res => {
            childReplyContent.value = ''
            props.list[childReply.value.index].replyInputVisble = false
            ElMessage({
                message: '评论成功.',
                type: 'success',
            })
        })
    }
</script>

<style scoped>
    .replyBtn {
        margin-left: 20px;
    }
    .replyBtn:hover {
        cursor: pointer;
        background-color: #E5E9EF;
    }
    ::v-deep .el-textarea__inner {
        height: 90px !important;
    }
    .el-divider--horizontal {
        margin: 10px 0;
    }
</style>
