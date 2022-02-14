package com.ssafy.Azit.service;

import com.ssafy.Azit.model.Diary;
import com.ssafy.Azit.model.User;
import com.ssafy.Azit.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    @Override
    public Diary createDiary(Diary diary) {

        return diaryRepository.save(diary);
    }


    @Override
    public Diary getDiary(long diaryId) {
        return diaryRepository.findByDiaryId(diaryId).get();
    }

    @Override
    public List<Diary> listDiary(String userId, int page) {

        User user = new User();
        user.setUserId(userId);

        List<Diary> diaries = diaryRepository.findAll();
        List<Diary> diaryList = new ArrayList<>();

        for (Diary diary : diaries) {
//            System.out.println(diary.getUser().getUserId());
            if (diary.getUser().getUserId().equals(userId)) {
                diaryList.add(diary);
            }
        }

        int start = (page-1)*10;
        int end = page*10;
        if(end > diaryList.size()) {
            end = diaryList.size();
        }
        List<Diary> subList = new ArrayList<>(diaryList.subList(start, end));

        return subList;
    }

    @Override
    public List<Diary> listSearchDiary(String userId, String title, int page) {

        User user = new User();
        user.setUserId(userId);

        List<Diary> diaries = diaryRepository.findByTitleContaining(title);
        List<Diary> diaryList = new ArrayList<>();

        for (Diary diary : diaries) {
            if (diary.getUser().getUserId().equals(userId)) {
                diaryList.add(diary);
            }
        }

        int start = (page-1)*10;
        int end = page*10;
        if(end > diaryList.size()) {
            end = diaryList.size();
        }
        List<Diary> subList = new ArrayList<>(diaryList.subList(start, end));

        return subList;
    }

    @Override
    public Diary updateDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    @Override
    public int deleteDiary(long diaryId) {

        diaryRepository.deleteById(diaryId);
        return 1;
    }


}
