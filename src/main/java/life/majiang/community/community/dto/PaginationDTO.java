package life.majiang.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawn on 2020/3/26 22:26.
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;
    private int page;
    private int totalPages;
    private int totalQuestions;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(int questionCount, Integer page, Integer size) {
        this.page = page;
        this.totalQuestions = questionCount;
        if(questionCount%size==0)
            this.totalPages = questionCount/size;
        else
            this.totalPages = questionCount/size + 1;

        //是否展示上一页
        if(page==1)
            showPrevious = false;
        else
            showPrevious = true;
        //是否展示下一页
        if(page==totalPages){
            showNext = false;
        }
        else{
            showNext = true;
        }
        //是否展示第一页
        if(page>4)
            showFirstPage = true;
        else
            showFirstPage = false;
        if(page+3<totalPages)
            showLastPage = true;
        else
            showLastPage = false;
        int firstIndex = page>=4?page-3:1;
        int lastIndex = page+3<=totalPages?page+3:totalPages;
        for(int i=firstIndex;i<=lastIndex;i++)
            pages.add(i);

    }
}
