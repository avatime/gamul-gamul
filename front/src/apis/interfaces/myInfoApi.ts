import { LimitPriceNoticeInfo } from "../responses/limitPriceNoticeInfo";

export interface MyInfoApi {
  /**
   * 식재료 알러지 등록
   * @param userName 유저 ID
   * @param ingredientIdList 식재료 ID 리스트
   */
  putAllergy(userName: string, ingredientIdList: number[]): Promise<void>;
  /**
   * 식재료 알러지 목록 조회
   * @param userName 유저 ID
   */
  getAllergyIngredientList(userName: string): Promise<number[]>;
  /**
   * 식재료 상한가 하한가 알림 등록
   * @param userName 유저 ID
   * @param limitPriceNoticeInfoList 식재료 상한가 하한가 정보
   */
  postLimitPriceNotice(
    userName: string,
    limitPriceNoticeInfoList: LimitPriceNoticeInfo[]
  ): Promise<void>;
  /**
   * 식재료 상한가 하한가 목록 조회
   * @param userName 유저 ID
   */
  getLimitPriceList(userName: string): Promise<LimitPriceNoticeInfo[]>;
  /**
   * 웹 푸시 구독 객체 등록
   * @param userName 유저 ID
   * @param subscription 웹 푸시 구독 객체
   */
  postSubscription(userName: string, subscription: PushSubscription): Promise<void>;
}
