import service from "../service";
import Moment from 'moment';

class StockService {

  getStocks(cCode, sDate, eDate) {
    let startDate = Moment(sDate).format('YYYY-MM-DD');
    let endDate = Moment(eDate).format('YYYY-MM-DD');

    let data = service.post('stock/get/' + cCode + '/' + startDate + '/' + endDate);
    return data;
  }

  addStock(data,companyCode) {
    return service.post("stock/add/"+companyCode, data);
  }
}

export default new StockService();