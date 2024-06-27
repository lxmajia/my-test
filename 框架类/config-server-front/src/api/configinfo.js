import request from '@/utils/request'



export function getConfigInfo(data) {
  return request({
    url: '/manager/getConfigInfoList',
    method: 'post',
    data
  })
}
