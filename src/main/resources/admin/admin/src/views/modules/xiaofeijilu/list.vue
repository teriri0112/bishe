<template>
  <div class="main-content">
    <el-card>
      <div slot="header">消费记录</div>
      <el-form :inline="true" :model="searchForm">
        <el-form-item label="订单流水号">
          <el-input v-model="searchForm.goodsShouyinUuidNumber" clearable placeholder="请输入订单流水号" />
        </el-form-item>
        <el-form-item label="支付方式">
          <el-select v-model="searchForm.goodsShouyinTypes" clearable placeholder="请选择支付方式">
            <el-option v-for="item in goodsShouyinTypesOptions" :key="item.id" :label="item.indexName" :value="item.codeIndex" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="success" @click="search">查询</el-button>
          <el-button @click="resetSearch">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="dataList" border v-loading="dataListLoading" style="width:100%">
        <el-table-column type="index" label="#" width="60" />
        <el-table-column prop="goodsShouyinUuidNumber" label="订单流水号" min-width="220" />
        <el-table-column prop="goodsShouyinTruePrice" label="实付金额" width="120" />
        <el-table-column prop="goodsShouyinValue" label="支付方式" width="120" />
        <el-table-column prop="insertTime" label="支付时间" min-width="180" />
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button
              v-if="isAuth('xiaofeijilu','查看')"
              type="primary"
              size="mini"
              @click="goDetail(scope.row)">
              查看明细
            </el-button>
          </template>
        </el-table-column>
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
      searchForm: {
        goodsShouyinUuidNumber: '',
        goodsShouyinTypes: ''
      },
      goodsShouyinTypesOptions: [],
      dataList: [],
      dataListLoading: false,
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0
    }
  },
  created() {
    this.getDataList()
    this.getGoodsShouyinTypesOptions()
  },
  methods: {
    search() {
      this.pageIndex = 1
      this.getDataList()
    },
    resetSearch() {
      this.searchForm = { goodsShouyinUuidNumber: '', goodsShouyinTypes: '' }
      this.search()
    },
    getGoodsShouyinTypesOptions() {
      this.$http({
        url: 'dictionary/page',
        method: 'get',
        params: { page: 1, limit: 100, dicCode: 'goods_shouyin_types' }
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.goodsShouyinTypesOptions = data.data.list || []
        }
      })
    },
    getDataList() {
      this.dataListLoading = true
      const params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
        order: 'desc',
        goodsShouyinDelete: 1
      }
      if (this.searchForm.goodsShouyinUuidNumber) {
        params.goodsShouyinUuidNumber = `%${this.searchForm.goodsShouyinUuidNumber}%`
      }
      if (this.searchForm.goodsShouyinTypes) {
        params.goodsShouyinTypes = this.searchForm.goodsShouyinTypes
      }
      this.$http({
        url: 'goodsShouyin/page',
        method: 'get',
        params
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
    },
    goDetail(row) {
      this.$router.push({
        path: '/xiaofeijiluDetail',
        query: { goodsShouyinId: row.id, goodsShouyinUuidNumber: row.goodsShouyinUuidNumber }
      })
    }
  }
}
</script>
