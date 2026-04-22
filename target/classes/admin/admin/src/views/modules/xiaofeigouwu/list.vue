<template>
  <div class="main-content">
    <el-card>
      <div slot="header">商品浏览与购物</div>
      <el-form :inline="true" :model="searchForm">
        <el-form-item label="商品名称">
          <el-input v-model="searchForm.goodsName" clearable placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品类型">
          <el-select v-model="searchForm.goodsTypes" clearable placeholder="请选择商品类型">
            <el-option v-for="item in goodsTypesOptions" :key="item.id" :label="item.indexName" :value="item.codeIndex" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="success" @click="search">查询</el-button>
          <el-button @click="resetSearch">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="dataList" border v-loading="dataListLoading" style="width: 100%">
        <el-table-column type="index" label="#" width="60" />
        <el-table-column prop="goodsUuidNumber" label="商品编号" min-width="130" />
        <el-table-column prop="goodsName" label="商品名称" min-width="140" />
        <el-table-column label="商品照片" width="120">
          <template slot-scope="scope">
            <img v-if="scope.row.goodsPhoto" :src="scope.row.goodsPhoto" width="60" height="60" />
            <span v-else>无图片</span>
          </template>
        </el-table-column>
        <el-table-column prop="goodsValue" label="商品类型" width="120" />
        <el-table-column prop="goodsKucunNumber" label="库存" width="100" />
        <el-table-column prop="goodsXiaoshouMoney" label="售价" width="100" />
        <el-table-column label="购买数量" width="140">
          <template slot-scope="scope">
            <el-input-number v-model="buyNumberMap[scope.row.id]" :min="1" :max="scope.row.goodsKucunNumber || 1" size="mini" />
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button
              v-if="isAuth('xiaofeigouwu','购买')"
              type="primary"
              size="mini"
              @click="addToCart(scope.row)">
              加入清单
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

    <el-card style="margin-top: 16px">
      <div slot="header" style="display:flex;justify-content:space-between;align-items:center;">
        <span>购物清单</span>
        <span style="color:#f56c6c;font-weight:600;">总价：¥{{ totalMoney.toFixed(2) }}</span>
      </div>
      <el-form :inline="true">
        <el-form-item label="支付方式">
          <el-select v-model="goodsShouyinTypes" placeholder="请选择支付方式" style="width: 220px">
            <el-option v-for="item in goodsShouyinTypesOptions" :key="item.id" :label="item.indexName" :value="item.codeIndex" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button v-if="isAuth('xiaofeigouwu','支付')" type="success" @click="submitGoodsShouyinData">提交支付</el-button>
          <el-button @click="clearCart">清空清单</el-button>
        </el-form-item>
      </el-form>

      <el-table :data="cartList" border>
        <el-table-column type="index" label="#" width="60" />
        <el-table-column prop="goodsName" label="商品名称" min-width="150" />
        <el-table-column prop="goodsXiaoshouMoney" label="售价" width="100" />
        <el-table-column prop="goodsKucunNumber" label="库存" width="100" />
        <el-table-column label="数量" width="180">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.goodsShouyinListNumber" :min="1" :max="scope.row.goodsKucunNumber || 1" size="mini" />
          </template>
        </el-table-column>
        <el-table-column label="小计" width="120">
          <template slot-scope="scope">
            {{ (scope.row.goodsXiaoshouMoney * scope.row.goodsShouyinListNumber).toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="120">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="removeCart(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchForm: {
        goodsName: '',
        goodsTypes: ''
      },
      goodsTypesOptions: [],
      goodsShouyinTypesOptions: [],
      goodsShouyinTypes: '',
      dataList: [],
      dataListLoading: false,
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      buyNumberMap: {},
      cartList: []
    }
  },
  computed: {
    totalMoney() {
      let total = 0
      this.cartList.forEach(item => {
        total += Number(item.goodsXiaoshouMoney || 0) * Number(item.goodsShouyinListNumber || 0)
      })
      return total
    }
  },
  created() {
    this.getDataList()
    this.getGoodsTypesOptions()
    this.getGoodsShouyinTypesOptions()
  },
  methods: {
    search() {
      this.pageIndex = 1
      this.getDataList()
    },
    resetSearch() {
      this.searchForm = { goodsName: '', goodsTypes: '' }
      this.search()
    },
    getDataList() {
      this.dataListLoading = true
      const params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
        order: 'desc',
        goodsDelete: 1,
        shangxiaTypes: 1
      }
      if (this.searchForm.goodsName) {
        params.goodsName = `%${this.searchForm.goodsName}%`
      }
      if (this.searchForm.goodsTypes) {
        params.goodsTypes = this.searchForm.goodsTypes
      }
      this.$http({
        url: 'goods/page',
        method: 'get',
        params
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list || []
          this.totalPage = data.data.total || 0
          this.dataList.forEach(item => {
            if (!this.buyNumberMap[item.id]) {
              this.$set(this.buyNumberMap, item.id, 1)
            }
          })
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    getGoodsTypesOptions() {
      this.$http({
        url: 'dictionary/page',
        method: 'get',
        params: { page: 1, limit: 100, dicCode: 'goods_types' }
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.goodsTypesOptions = data.data.list || []
        }
      })
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
    sizeChangeHandle(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    currentChangeHandle(val) {
      this.pageIndex = val
      this.getDataList()
    },
    addToCart(row) {
      const index = this.cartList.findIndex(item => item.id === row.id)
      const number = Number(this.buyNumberMap[row.id] || 1)
      if (index >= 0) {
        this.cartList[index].goodsShouyinListNumber = Number(this.cartList[index].goodsShouyinListNumber) + number
      } else {
        this.cartList.push({
          id: row.id,
          goodsName: row.goodsName,
          goodsXiaoshouMoney: row.goodsXiaoshouMoney,
          goodsKucunNumber: row.goodsKucunNumber,
          goodsShouyinListNumber: number
        })
      }
      this.$message.success('已加入购物清单')
    },
    removeCart(index) {
      this.cartList.splice(index, 1)
    },
    clearCart() {
      this.cartList = []
    },
    submitGoodsShouyinData() {
      if (!this.goodsShouyinTypes) {
        this.$message.error('请选择支付方式')
        return
      }
      const map = {}
      this.cartList.forEach(item => {
        map[item.id] = item.goodsShouyinListNumber
      })
      if (JSON.stringify(map) === '{}') {
        this.$message.error('购物清单不能为空')
        return
      }
      this.$http({
        url: 'goodsShouyin/shouyin',
        method: 'post',
        data: {
          map,
          goodsShouyinTypes: this.goodsShouyinTypes
        }
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message.success('支付成功')
          this.clearCart()
          this.goodsShouyinTypes = ''
          this.getDataList()
        } else {
          this.$message.error(data.msg || '支付失败')
        }
      })
    }
  }
}
</script>
