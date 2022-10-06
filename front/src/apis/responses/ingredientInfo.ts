export interface IngredientInfo {
  ingredient_id: number; // 아이디,
  name: string; // 이름,
  price: number; // 가격,
  unit: string; // 단위,
  quantity: number; // 단량,
  volatility: number; // 변동폭 퍼센테이지(예) 20, -20,
  allergy: boolean; // 알러지 등록한 식재료 인지,
  bookmark: boolean; // 찜 여부,
  basket: boolean; // 바구니 여부,
  high_class_id: number; // 대분류 id
  high_class_name: string; // 대분류 이름
  views: number; // 조회수;
  my_quantity: number; // 나만의 요리법 단량
}
