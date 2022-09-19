package com.example.postgresoncloudsql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.KenkoRecord;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CloudSqlClient {

  @Autowired
  private JdbcService service;

  @RequestMapping("/")
  public String hello() {
    log.info("hello world");
    return "helloworld";
  }

  @RequestMapping("/list")
  public List<KenkoRecord> list() {
    try {
      log.info("ToDo");
      return service.findAll();
    } catch (Exception e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  // POST <host>/crud
  @PostMapping(value = "crud")
  public String create(
    @RequestBody KenkoRecord param
  ) {
    try {
      log.info(String.format("[CREATE]Argument info id:%d name:%s food:%s", param.getId(), param.getName(), param.getFood()));
      return service.create(param.getId(), param.getName(), param.getFood());
    } catch (Exception e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  // GET <host>/crud?id=xxx
  @GetMapping(value = "crud")
  public List<KenkoRecord> read(
    @RequestParam(value="id", required=false) Long id
  ) {
    try {
      log.info(String.format("[READ]Argument info id:%d", id));
      return service.read(id);
    } catch (Exception e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  // PUT <host>/crud
  @PutMapping(value = "crud")
  public String update(
    @RequestBody KenkoRecord param
  ) {
    try {
      log.info(String.format("[UPDATE]Argument info id:%d name:%s food:%s", param.getId(), param.getName(), param.getFood()));
      return service.update(param.getId(), param.getName(), param.getFood());
     } catch (Exception e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  // DELETE <host>/crud?id=xxx
  @DeleteMapping(value = "crud")
  public String delete(
    @RequestParam(value="id", required=true) Long id
  ) {
    try {
      log.info(String.format("[DELETE]Argument info id:%d", id));
      return service.delete(id);
    } catch (Exception e) {
      log.error(e.getMessage());
      e.printStackTrace();
    }
    return null;
  }


}