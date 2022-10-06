import { IngredientInfo } from "./ingredientInfo";
import { PriceTransitionInfo } from "./priceTransitionInfo";

export interface MyRecipeDetailInfo {
  total_price: number; // 식재료 총 금액
  ingredient_list: IngredientInfo[];
  price_transition_info: PriceTransitionInfo;
  image_path: string; //사용자가 업로드한 나만의 요리법 사진 경로
  name: string; // 나만의 요리법 이름
}
