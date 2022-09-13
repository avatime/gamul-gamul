import { SearchResult } from "./response/searchResult";

export interface SearchApi {
  /**
   * 통합 검색
   * @param keyword 검색어
   */
  search(keyword: string): Promise<SearchResult>;
}
