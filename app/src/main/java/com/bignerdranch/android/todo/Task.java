package com.bignerdranch.android.todo;

/**
 * Created by panwa on 2/25/2017.
 */

public class Task {
int _Id;
    String _Heading;
    String _Detail;

    public int get_Id() {
        return _Id;
    }

    public void set_Id(int _Id) {
        this._Id = _Id;
    }

    public String get_Detail() {
        return _Detail;
    }

    public void set_Detail(String _Detail) {
        this._Detail = _Detail;
    }

    public String get_Heading() {
        return _Heading;
    }

    public void set_Heading(String _Heading) {
        this._Heading = _Heading;
    }

    public Task(int _Id, String _Heading, String _Detail) {
        this._Id = _Id;
        this._Heading = _Heading;
        this._Detail = _Detail;
    }

    public Task(String _Heading, String _Detail) {
        this._Heading = _Heading;
        this._Detail = _Detail;
    }
    public Task()
    {

    }

    public Task(int _Id) {
        this._Id = _Id;
    }
}