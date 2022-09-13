import { PriceInfo } from "./priceInfo";

export interface SaleInfo {
  daily: PriceInfo[];
  monthly: PriceInfo[];
  yearly: PriceInfo[];
}
