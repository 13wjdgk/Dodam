package com.example.dodam.controller.diary;

import com.example.dodam.domain.diary.Diary;
import com.example.dodam.domain.diary.DiaryDetail;
import com.example.dodam.domain.diary.DiaryDetailImg;
import com.example.dodam.domain.diary.DiaryList;
import com.example.dodam.service.diary.DiaryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
public class DiaryController {

    private final DiaryService diaryService ;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }



    // 다이어리 목록 조회
    @GetMapping("/diary/{id}")
    public List<DiaryList> getDiaryList(@PathVariable Integer id){
        List<DiaryList>  diaryList = diaryService.findDiarys(id);
        return diaryList;
    }

    // 다이어리 디테일 조회
    @GetMapping("/diary/detail/{id}")
    public Optional<DiaryDetailImg> getDiaryDetail(@PathVariable Integer id){
        Optional<DiaryDetail> diary = diaryService.findDiary(id);
        DiaryDetailImg diaryDetailImg = new DiaryDetailImg();
        diaryDetailImg.setId(diary.get().getId());
        diaryDetailImg.setTitle(diary.get().getTitle());
        diaryDetailImg.setContent(diary.get().getContent());
        diaryDetailImg.setOneWord(diary.get().getOneWord());
        try {
            byte[] imgData =  diaryService.getImage("image/diary/0.png");

            diaryDetailImg.setImg(imgData);



        }
        catch (Exception e) {
            System.out.print("이미지 조회 실패");
        }
        return Optional.of(diaryDetailImg);
    }

    //다이어리 등록
//    @PostMapping("members/new")
//    public String create(MemberForm form){
//        Member member = new Member();
//        member.setName(form.getName());
//        memberService.join(member);
//        return "redirect:/";
//    }
    @PostMapping("/diary")
    public String addDiary(@RequestBody Diary diary){
        diaryService.addDiary((diary));

        return "성공";
    }

    @PutMapping("/diary")
    public String putDiary(@RequestBody Diary diary){
        diaryService.updateDiary(diary);
        return "성공";
    }

    @DeleteMapping("/diary/{id}")
    public String deleteDiary(@PathVariable Integer id){
        //해당 id 다이어리 존재 여부 확인
        diaryService.deleteDiary(id);
        return "성공";
    }
    @PostMapping("/diary/img/")
    public String addDiary( @RequestBody MultipartFile img) throws Exception {
//        Integer id = diaryimg.getId();
//        MultipartFile img = diaryimg.getImg();
        System.out.printf("pass");
        diaryService.saveDiaryImag(img);
        System.out.printf("pass");

        return "성공";
    }


//    @GetMapping("/diary")
//    public Diary getDiary(){
//        Diary diary = new Diary();
//        diary.id = 1;
//        diary.userId = 1;
//        diary.imgPath = "../";
//        diary.date = new Date();
//        diary.feel = "좋음";
//        diary.oneWord = "힘들다..";
//        return diary;
//    }


//    @GetMapping("/info")
//    public String home(){
//        return "hello";
//    }

//
//    @PostMapping("/diary/add")
//    public String
}