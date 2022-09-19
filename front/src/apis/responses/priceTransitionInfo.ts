import { SaleInfo } from "./saleInfo";

export interface PriceTransitionInfo {
  before_price: number; //어제 금액 (꼭 어제가 아니여도 되니까 현재 이전의 바로 것)
  price: number; // 현재 금액
  wholesales: SaleInfo; // 도매 가격 추이
  retailsales: SaleInfo; // 소매 가격 추이
  pastvol: number; // 어제 물가 변동률
  todayvol: number; // 오늘 물가 변동률
}
