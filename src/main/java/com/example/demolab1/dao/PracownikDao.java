package com.example.demolab1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.demolab1.beans.Pracownik;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
public class PracownikDao {
    JdbcTemplate template;
    public void setTemplate(JdbcTemplate template) {
        this.template = template; //wstrzyknięcie przez metodę set
    }
    public int save(Pracownik p) {
        String sql = "insert into pracownik (nazwisko,pensja,firma) "
                + "values('" + p.getNazwisko() + "'," + p.getPensja()
                + ",'" + p.getFirma() + "')";
        return template.update(sql);
    }
    public List<Pracownik> getAll() {
        return template.query("select * from pracownik",
                (rs, row) -> {
            Pracownik e = new Pracownik();
            e.setId(rs.getInt(1));
            e.setNazwisko(rs.getString(2));
            e.setPensja(rs.getFloat(3));
            e.setFirma(rs.getString(4));
            return e;
        });
    }
    public void delete(int id) {
        String sql = "delete from pracownik where pracownik.id=" + id;
        template.update(sql);
    }
}
