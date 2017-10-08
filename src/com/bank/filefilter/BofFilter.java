/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.filefilter;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author chihoang
 */
public class BofFilter extends FileFilter {

  public BofFilter() { }

  @Override
  public boolean accept(File f) {
    return (f.isDirectory() || f.getName().toLowerCase().endsWith(".bof"));
  }

  @Override
  public String getDescription() {
    return "Bank Object File";
  }
  
}
