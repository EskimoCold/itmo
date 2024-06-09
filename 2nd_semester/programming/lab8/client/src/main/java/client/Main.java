package client;

import client.app.App;
import client.collections.CommandArgsDTO;
import client.handlers.CommandHandler;
import client.network.ClientExecuteScript;
import client.network.UDPClient;
import common.collections.LabWork;
import common.commands.Command;
import common.commands.auth.AuthCommand;
import common.commands.collection.CollectionCommand;
import common.commands.collection.CommandWithElement;
import common.commands.collection.ExecuteScript;
import common.commands.collection.Exit;
import common.exceptions.NotLoggedInException;
import common.handlers.IOHandler;
import common.network.Request;
import common.network.Response;
import common.network.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App.launch(App.class, args);
    }
}
