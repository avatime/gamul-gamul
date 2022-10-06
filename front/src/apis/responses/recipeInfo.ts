export interface RecipeInfo {
  recipe_id: number; // 레시피 ID
  image_path: string; // 이미지 경로
  name: string; // 레시피 이름
  desc: string; // 상세 정보 (예) “2인분 60분 이내 중급”
  bookmark: boolean; // 찜 여부
  views: number; // 조회수
}
