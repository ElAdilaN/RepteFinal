module cat.boscdelacoma.cafepaloma {
        opens cat.boscdelacoma.cafepaloma.model to javafx.base;

    requires javafx.controls;
    requires javafx.fxml;
requires java.sql ; 
    requires java.base;
    opens cat.boscdelacoma.cafepaloma to javafx.fxml;
    exports cat.boscdelacoma.cafepaloma;
}