<template>
    <div class="postContainer">
        <div style="display: flex;">

            <div style="width: 510px;">
                <div style="display: flex;width: 460px;padding: 0 25px;height: 80%;align-items: center;">
                    <div>
                        <div style="display: flex;align-items: center;padding-top: 3%;height: 15%;">
                            <div style="padding-right: 10px;">
                                <el-tag size="large" style="font-size: 14px;">置顶</el-tag>
                            </div>
                            <div class="titleBox">
                                SpringBoot底层源码分析
                            </div>
                        </div>

                        <!-- <div class="contentBox">
                            <div>{{contentMod.content}}</div>
                        </div> -->
                    </div>
                </div>

                <div style="height: 11%;padding: 0 25px;">
                                            <div>
                            <div>
                                <el-tag v-for="item in contentMod.label" :key="item.label" :type="item.type"
                                    effect="light" size="default" style="margin-right: 5px;">
                                    {{ item.label }}
                                </el-tag>
                            </div>
                        </div>
                </div>

                <div style="height: 9%;width: 460px;padding: 0 25px;background-color: #f7f7f7;border-bottom-left-radius: 13px;">
                    <div class="actionBox">
                        <div style="font-size: 15px;color: #7b9f9f;display: flex;align-items: center;">
                            <el-icon>
                                <Brush />
                            </el-icon>
                            <div style="margin-left: 3px;">2022-07-10</div>
                        </div>
                        <div style="font-size: 15px;color: #384c4c;display: flex;padding-left: 20px;color: #7b9f9f">
                            <div style="padding-right: 10px;display: flex;align-items: center;">
                                <el-icon>
                                    <Reading />
                                </el-icon>
                                <div style="margin-left: 3px;">20</div>
                            </div>
                            <div style="display: flex;align-items: center;">
                                <el-icon>
                                    <ChatRound />
                                </el-icon>
                                <div style="margin-left: 3px;">20</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div style="height: 250px;" class="imgBox">
                <img class="coverImg" src="../assets/Avatar.jpg" />
            </div>

        </div>


    </div>
</template>

<script setup>
    let contentMod = {
        content:'日常收集的一些链接，分享给大家，感谢支持。 😊可直接Ctrl+F全文搜索哦~ 工具🃏 内网穿透 UP云搜-阿里云盘 3D打印作品(可下载) 史上最好用的站长工具 4K高清壁纸 Charles在线破解工具 smallPDF--免费PDF转换 无标签限制版Xshell 无标签限制版Xftp 最',
        label:[
            { type: 'success', label: 'vue' },
            { type: 'success', label: 'springboot' }
        ]
    }

</script>

<style scoped>

    .postContainer {
        box-shadow: 0 4px 8px 6px rgb(113 118 122 / 6%);
        border-radius: 13px;
        /* padding: 10px 20px 10px 20px; */
        margin-bottom: 15px;
        transition: all .3s linear;
        background-color: white;
        height: 250px;
        width: 890px;
    }
    .postContainer:hover {
        box-shadow: 0 0 8px 0 rgb(0 0 0 / 20%);
        -webkit-box-shadow:  0 0 15px 2px rgb(0 0 0 / 20%);
        -webkit-transition: all .5s ease-out;
        transition: all .5s ease-out;
    }

    .postContainer:hover .imgBox img {
        transform: scale(1.1);
        overflow: hidden;
        border-top-right-radius: 13px;
        border-bottom-right-radius: 13px;
    }

    .titleBox {
        font-size: 22px;
        cursor: pointer;
        position: relative;
        display: inline-block;
    }
        
    .titleBox::before {
        content: "";
        position: absolute;
        left: 50%;
        bottom: -3px;
        width: 0;
        height: 3px;
        background: #778be6;
        transition: all .5s;
    }
        
    .titleBox:hover:before {
        width: 100%;
        left: 0;
        right: 0;
    }

    .titleBox:hover {
        color: #778be6;
    }

    .el-divider--horizontal {
        margin: 5px 0;
    }
    blockquote{
        margin: 0.8em 0;
        border-left: 5px solid #409eff;
        padding: 15px 15px;
        color: #242424;
        background-color: #ebeef5;
    }
    /* .contentBox {
        height: 140px;
        overflow:hidden;

        background-clip: text;
        background-image: -webkit-gradient(linear, 0 0, left bottom, from(#000), to(#fff));
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    } */
    .contentBox {
        color: #403f3f;
        padding-top: 10px;
        font-size: 15px;
        overflow:hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
    }
    .actionBox {
        padding: 2px 0 0 0;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    .coverImg {
        width: 380px;
        height: 250px;
        border-top-right-radius: 13px;
        border-bottom-right-radius: 13px;
    }

    .imgBox {
        width: 380px;
        height: 250px;
        overflow: hidden;
        border-top-right-radius: 13px;
        border-bottom-right-radius: 13px;
    }

    .imgBox img{
        transition: all 0.5s;
        overflow: hidden;
    }

</style>