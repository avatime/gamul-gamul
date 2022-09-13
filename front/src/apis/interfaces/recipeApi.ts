import { RecipeDetailInfo } from "../responses/recipeDetailInfo";
import { RecipeInfo } from "../responses/recipeInfo";
import { RecipeOrderInfo } from "../responses/recipeOrderInfo";
import { YoutubeInfo } from "../responses/youtubeInfo";

export enum RecipeOrderType {
  NAME_ASC = 1,
  VIEW_ASC,
}

export interface RecipeApi {
    /**
     * 요리법 목록 조회
     * @param orderType 정렬 타입
     * @param page 페이징 단위에서 현재 페이지 (1부터 시작)
     * @param size 페이징 단위 개수 (30이면 30개 씩 페이징 처리)
     */
  getRecipeList(orderType: RecipeOrderType, page: number, size: number): Promise<RecipeInfo[]>;
  /**
   * 요리법 with 바구니 조회
   * @param userName 유저 ID
   * @param orderType 정렬 타입
   * @param page 페이징 단위에서 현재 페이지 (1부터 시작)
   * @param size 페이징 단위 개수 (30이면 30개 씩 페이징 처리)
   */
  getRecipeWithBasketList(userName: string, orderType: RecipeOrderType, page: number, size: number): Promise<RecipeInfo[]>;
  /**
   * 요리법 찜 목록 조회
   * @param userName 유저 ID
   */
  getBookmarkRecipeList(userName: string): Promise<RecipeInfo[]>;
  /**
   * 요리법 상세 조회
   * @param recipeId 요리법 ID
   */
  getRecipeDetailInfo(recipeId: number): Promise<RecipeDetailInfo>;
  /**
   * 요리법 찜 등록/해제
   * @param userName 유저 ID
   * @param recipeId 요리법 ID
   */
  putBookmarkRecipe(userName: string, recipeId: number): Promise<void>;
  /**
   * 요리법 순서 조회
   * @param recipeId 요리법 ID
   */
  getRecipeOrderList(recipeId: number): Promise<RecipeOrderInfo[]>;
  /**
   * 요리법 속 재료 바구니에 모두 넣기
   * @param userName 유저 ID
   * @param recipeId 요리법 ID
   */
  putBasketAllRecipeIngredient(userName: string, recipeId: number): Promise<void>;
  /**
   * 인기 요리법 유튜브 목록 조회
   */
  getPopularYoutubeList(): Promise<YoutubeInfo[]>;
}
