package co.lowprofile.ooquo.controller;

import android.os.AsyncTask;

/**
 * Created by asilkaratas on 10/5/15.
 */
public abstract class Command
{
    public abstract void execute();
    public void executeInBackground()
    {
        new CommandTask().execute(this);
    }

    private static class CommandTask extends AsyncTask<Object, Void, Void>
    {
        @Override
        protected Void doInBackground(Object... params)
        {
            Command command = (Command)params[0];
            command.execute();

            return null;
        }
    }
}
