# CSC2002S Assignment 2 Makefile
# Nkosinathi Ntuli
# 2021/09/06

JAVAC=/usr/bin/javac

.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
DOCDIR=doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES= WordDictionary.class Score.class WordRecord.class WordPanel.class WordApp.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)
clean: 
	rm $(BINDIR)/*.class

run: $(CLASS_FILES)
	java -cp $(BINDIR) WordApp $(input)

docs:
	javadoc -d $(DOCDIR)/ $(SRCDIR)/*.java
