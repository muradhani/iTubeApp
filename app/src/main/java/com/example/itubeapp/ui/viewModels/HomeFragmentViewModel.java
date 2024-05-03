package com.example.itubeapp.ui.viewModels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.itubeapp.dao.UserDao;
import com.example.itubeapp.data.database.AppDatabase;

public class HomeFragmentViewModel extends ViewModel {
    private UserDao userDao;

    public void initaiteDatabae(Context context){
        userDao = AppDatabase.getDatabase(context).userDao();
    }
}
