import { HighClass } from "../responses/highClass";
import { IngredientDetailInfo } from "../responses/ingredientDetailInfo";
import { IngredientInfo } from "../responses/ingredientInfo";
import { OfflineMartInfo } from "../responses/offlineMartInfo";

export enum IngredientOrderType {
  NAME_ASC = 1, // 이름 사전 순 (오름차순)
  VIEW_ASC, // 조회수 큰 순
  VOLATILITY_ASC, // 변동폭 큰 순
  PRICE_DESC, // 가격 싼 순
}

export interface IngredientApi {
  /**
   * 식재료 목록 조회
   * @param orderType 정렬 타입
   * @param highClassId 대분류 ID
   */
  getIngredientList(orderType: IngredientOrderType, highClassId: number): Promise<IngredientInfo[]>;
  /**
   * 식재료 찜 목록 조회
   * @param userName 유저 ID
   */
  getBookmarkIngredientList(userName: string): Promise<IngredientInfo[]>;
  /**
   * 식재료 상세 조회
   * @param ingredientId 식재료 ID
   */
  getIngredientDetailInfo(ingredientId: number): Promise<IngredientDetailInfo>;
  /**
   * 식재료 대분류 목록 조회
   */
  getIngredientHighClassList(): Promise<HighClass[]>;
  /**
   * 식재료 찜 등록/해제
   * @param userName 유저 ID
   * @param ingredientId 식재료 ID
   */
  putBookmarkIngredient(userName: string, ingredientId: number): Promise<void>;
  /**
   * 식재료 바구니 등록/해제
   * @param userName 유저 ID
   * @param ingredientId 식재료 ID
   */
  putBasketIngredient(userName: string, ingredientId: number): Promise<void>;
  /**
   * 오프라인 마트 정보 조회
   * @param ingredientId 식재료 ID
   * @param southWestLatitude 지도 남서쪽 위도
   * @param southWestLongitude 지도 남서쪽 경도
   * @param northEastLatitude 지도 북동쪽 위도
   * @param northEastLongitude 지도 북동쪽 경도
   * @param latitude 지도 중앙 위도
   * @param longitude 지도 중앙 경도
   */
  getOfflineMartList(
    ingredientId: number,
    southWestLatitude: number,
    southWestLongitude: number,
    northEastLatitude: number,
    northEastLongitude: number,
    latitude: number,
    longitude: number
  ): Promise<OfflineMartInfo[]>;
  /**
   * 오프라인마트 상세 정보 조회 (마트에서 무얼 파는지 조회)
   * @param storeId 마트 ID
   */
  getOfflineMartDetailInfo(storeId: number): Promise<IngredientInfo[]>;
  /**
   * 식재료 바구니 목록 조회
   * @param userName 유저 ID
   */
  getBasketIngredientList(userName: string): Promise<IngredientInfo[]>;
  /**
   * 식재료 조회수 업데이트
   * @param ingredientId 식재료 ID
   */
  postIngredientView(ingredientId: number): Promise<void>;
}
