import { PriceInfo } from "./PriceInfo";

export interface SaleInfo {
  daily: PriceInfo[];
  monthly: PriceInfo[];
  yearly: PriceInfo[];
}
