export type SearchType = "keyword" | "ingredient" | "recipe";

export function saveSearchLocalStorage<T>(searchType: SearchType, newItem: T) {
  const item = localStorage.getItem(searchType);
  if (item) {
    const origin = JSON.parse(item) as T[];
    if (origin.includes(newItem)) {
      origin.splice(origin.indexOf(newItem), 1);
    }
    origin.splice(0, 0, newItem);
    localStorage.setItem(searchType, JSON.stringify(origin));
  } else {
    localStorage.setItem(searchType, JSON.stringify([newItem]));
  }
}

export function deleteSearchLocalStorage<T>(searchType: SearchType, newItem: T) {
  const item = localStorage.getItem(searchType);
  if (item) {
    const origin = JSON.parse(item) as T[];
    if (origin.includes(newItem)) {
      origin.splice(origin.indexOf(newItem), 1);
    }
    localStorage.setItem(searchType, JSON.stringify(origin));
  }
}

export function getSearchLocalStorage<T>(
  searchType: SearchType,
  filter: (item: T) => boolean
): T[] {
  const item = localStorage.getItem(searchType);
  if (item) {
    const origin = JSON.parse(item) as T[];
    return origin.filter(filter);
  }

  return [];
}
