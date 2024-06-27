import request from '@/utils/request'

export function getAppModuleList(data) {
  return request({
    url: '/manager/appModuleList',
    method: 'post',
    data
  })
}
