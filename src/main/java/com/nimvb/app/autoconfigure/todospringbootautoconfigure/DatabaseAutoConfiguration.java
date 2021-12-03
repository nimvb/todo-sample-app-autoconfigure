package com.nimvb.app.autoconfigure.todospringbootautoconfigure;

import com.nimvb.app.database.Database;
import com.nimvb.app.database.DefaultDatabase;
import com.nimvb.app.database.document.BoardDocument;
import com.nimvb.app.database.document.Document;
import com.nimvb.app.database.document.ItemDocument;
import com.nimvb.app.database.document.TodoDocument;
import com.nimvb.app.database.model.Board;
import com.nimvb.app.database.model.Item;
import com.nimvb.app.database.model.Todo;
import com.nimvb.app.database.sequence.GuidSequenceGenerator;
import com.nimvb.app.database.sequence.IntegerSequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Database.class)
public class DatabaseAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public Database database() {
        return new DefaultDatabase(new BoardDocument(new GuidSequenceGenerator()),new TodoDocument(new IntegerSequenceGenerator()),new ItemDocument(new IntegerSequenceGenerator()));
    }

    @ConditionalOnBean(Database.class)
    @ConditionalOnMissingBean(name = "boardDocument")
    @Bean(name = "boardDocument")
    @Autowired
    public Document<Board,String> boardDocument(Database database){
        return database.boards();
    }


    @ConditionalOnBean(Database.class)
    @ConditionalOnMissingBean(name = "todoDocument")
    @Bean(name = "todoDocument")
    @Autowired
    public Document<Todo,Integer> todoDocument(Database database){
        return database.todos();
    }


    @ConditionalOnBean(Database.class)
    @ConditionalOnMissingBean(name = "itemDocument")
    @Bean(name = "itemDocument")
    @Autowired
    public Document<Item,Integer> itemDocument(Database database){
        return database.items();
    }
}
