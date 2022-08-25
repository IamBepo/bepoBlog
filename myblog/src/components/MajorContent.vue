<template>
    <div>
        <div>
            <PostBox></PostBox>
        </div>
        <div>
            <div class="pagination">
                <div>
                    <el-pagination background layout="prev, pager, next" :total="1000">
                    </el-pagination>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import PostBox from '@/components/PostBox.vue';
export default {
    name: "MajorContent",
    components: { PostBox }
}
</script>

<style scoped>
    .pagination {
        text-align: center;
    }
    ::v-deep .el-pagination.is-background .el-pager li{
        background-color: #ededed;
    }
    ::v-deep .el-pagination.is-background .btn-prev, ::v-deep .el-pagination.is-background .btn-next{
        background-color: #ededed;
    }
</style>