import request from '@/utils/request'

export function getAppModuleList(params) {
  return request({
    url: '/manager/appModuleList?pageNum=1&pageSize=10',
    method: 'get',
    params
  })
}
