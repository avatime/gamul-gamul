type RecentType = "ingredient" | "recipe";

export interface RecentSearch {
  recentType: RecentType;
  id: number;
  name: string;
}

const key = "recentSearch";

export function saveRecentSearchLocalStorage(recentType: RecentType, id: number, name: string) {
  const newItem: RecentSearch = {
    recentType,
    id,
    name,
  };
  const item = localStorage.getItem(key);
  if (item) {
    const origin = JSON.parse(item) as RecentSearch[];
    const idx = origin.findIndex((v) => v.id === id);
    if (0 <= idx) {
      origin.splice(idx, 1);
    }
    origin.splice(0, 0, newItem);
    localStorage.setItem(key, JSON.stringify(origin));
  } else {
    localStorage.setItem(key, JSON.stringify([newItem]));
  }
}

export function getRecentSearchLocalStorage<T>(): T[] {
  const item = localStorage.getItem(key);
  if (item) {
    return JSON.parse(item) as T[];
  }

  return [];
}
