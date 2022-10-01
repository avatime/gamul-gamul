export interface NotificationInfo {
  ingredient_id: number; // 식재료 ID
  title: string; // "고추 상한가 알림” 또는 “고추 하한가 알림"
  message: string; // “고추가 1,000원/100g 까지 떨어졌어요! 지금이 바로 기회!!" 또는 “양파가 1,000원/100g 까지 올랐어요! 다음을 기약해요.”
}
