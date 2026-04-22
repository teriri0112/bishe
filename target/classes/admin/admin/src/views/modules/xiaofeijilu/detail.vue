<template>
  <div class="main-content">
    <el-card>
      <div slot="header" style="display:flex;justify-content:space-between;align-items:center;">
        <span>消费明细（订单号：{{ goodsShouyinUuidNumber || '-' }}）</span>
        <el-button size="mini" @click="goBack">返回</el-button>
      </div>

      <el-table :data="dataList" border v-loading="dataListLoading" style="width:100%">
        <el-table-column type="index" label="#" width="60" />
        <el-table-column prop="goodsUuidNumber" label="商品编号" min-width="130" />
        <el-table-column prop="goodsName" label="商品名称" min-width="150" />
        <el-table-column label="商品图片" width="120">
          <template slot-scope="scope">
            <img v-if="scope.row.goodsPhoto" :src="scope.row.goodsPhoto" width="60" height="60" />
            <span v-else>无图片</span>
          </template>
        </el-table-column>
        <el-table-column prop="goodsValue" label="商品类型" width="120" />
        <el-table-column prop="goodsXiaoshouMoney" label="单价" width="100" />
        <el-table-column prop="goodsShouyinListNumber" label="购买数量" width="100" />
        <el-table-column label="小计" width="120">
          <template slot-scope="scope">
            {{ (scope.row.goodsXiaoshouMoney * scope.row.goodsShouyinListNumber).toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column prop="insertTime" label="购买时间" min-width="170" />
      </el-table>

      <el-pagination
        style="margin-top: 16px; text-align: right"
        layout="total, sizes, prev, pager, next"
        :current-page="pageIndex"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageSize"
        :total="totalPage"
        @size-change="sizeChangeHandle"
        @current-change="currentChangeHandle" />
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      goodsShouyinId: '',
      goodsShouyinUuidNumber: '',
      dataList: [],
      dataListLoading: false,
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0
    }
  },
  created() {
    this.goodsShouyinId = this.$route.query.goodsShouyinId
    this.goodsShouyinUuidNumber = this.$route.query.goodsShouyinUuidNumber
    this.getDataList()
  },
  methods: {
    goBack() {
      this.$router.push('/xiaofeijilu')
    },
    getDataList() {
      if (!this.goodsShouyinId) {
        this.$message.error('缺少订单参数')
        return
      }
      this.dataListLoading = true
      this.$http({
        url: 'goodsShouyinList/page',
        method: 'get',
        params: {
          page: this.pageIndex,
          limit: this.pageSize,
          sort: 'id',
          order: 'desc',
          goodsShouyinId: this.goodsShouyinId
        }
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list || []
          this.totalPage = data.data.total || 0
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    sizeChangeHandle(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    currentChangeHandle(val) {
      this.pageIndex = val
      this.getDataList()
    }
  }
}
</script>
