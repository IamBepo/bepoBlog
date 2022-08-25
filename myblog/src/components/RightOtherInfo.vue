<template>
    <div class="otherContainer">
        <div class="tagBox">
            <el-tag v-for="item in list" :key="item.id" :type="tagType[item.id%5].type" effect="light" size="large" class="tagS"
                @click="tagFun(item.id)">
                {{ item.name }}
            </el-tag>
        </div>
    </div>
</template>

<script setup>
    defineProps({
        list:Object
    })
    let tagType = [
        { type: ''},
        { type: 'success'},
        { type: 'info'},
        { type: 'danger'},
        { type: 'warning'}
    ]
    
    function tagFun(value) {
        console.log(value + "被点击")
    }

</script>

<style scoped>

    .otherContainer {
        box-shadow: 0 0 4px 0 rgb(0 0 0 / 20%);
        border-radius: 5px;
        text-align: center;
        padding: 20px 0 10px 0;
        transition: all .3s linear;
        background-color: white;
    }
    .otherContainer:hover {
        box-shadow: 0 0 8px 0 rgb(0 0 0 / 20%);
        -webkit-box-shadow:  0 0 15px 0 rgb(0 0 0 / 20%);
        -webkit-transition: all .5s ease-out;
        transition: all .5s ease-out;
    }
    .tagS {
        margin: 3px;
        cursor: pointer;
        font-size: 14px;
        transition: all 0.5s ease-in-out;
    }
    .tagS:hover {
        transition: transform 0.5s;
        transform: scale(1.1);
        -webkit-transition: transform 0.5s;
        -moz-transition: transform 0.5s;
        -webkit-transform: scale(1.1);
        -moz-transform: scale(1.1);
    }
</style>