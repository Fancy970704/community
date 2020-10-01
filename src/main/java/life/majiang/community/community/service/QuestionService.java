package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawn on 2020/3/11 20:58.
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Value("${questions.page.size}")
    private Integer size;
    public PaginationDTO list(Integer page) {
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.getAllQuestions(offset, size);
        PaginationDTO paginationDTO = createPaginationDTO(questionList);
        paginationDTO.setPage(page);
        int questionCount = questionMapper.count();
        paginationDTO.setPagination(questionCount, page, size);
        return paginationDTO;
    }

    public PaginationDTO list(User user, Integer page) {
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.getUserQuestions(user.getId(),offset, size);
        PaginationDTO paginationDTO = createPaginationDTO(questionList);
        paginationDTO.setPage(page);
        int questionCount = questionMapper.countByCreator(user.getId());
        paginationDTO.setPagination(questionCount, page, size);
        return paginationDTO;
    }

    private PaginationDTO createPaginationDTO(List<Question> questionList){
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questionList) {
            User temp = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(temp);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOList(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.getQuestionById(id);
        User user = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdateQuestion(Question question) {
        if(question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.createQuestion(question);
        }
        else{
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateQuestion(question);
        }
    }
}
