package com.example.cse464_1610329_midtermproject;

import android.os.Environment;

import com.example.cse464_1610329_midtermproject.model.Experience;
import com.example.cse464_1610329_midtermproject.model.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PDFMaker {

    private User user_information;
    private ArrayList<Experience> user_experiences;
    private String variant;
    private String mFilePath;

    public PDFMaker(User user_information, ArrayList<Experience> user_experiences,String variant,String mFilePath) {
        this.user_information = user_information;
        this.user_experiences = user_experiences;
        this.variant=variant;
        this.mFilePath=mFilePath;
    }

    //simple no color
    public void makeVariant1(){
        
        Font t_header=new Font(Font.FontFamily.TIMES_ROMAN,13.0f,Font.BOLD ,BaseColor.BLACK);
        Font t_item=new Font(Font.FontFamily.TIMES_ROMAN,10.0f,Font.NORMAL ,BaseColor.BLACK);
        float[] colWidths={1f,2f};

        Chunk linebreak=new Chunk(new LineSeparator());

        PdfPTable table_main=new PdfPTable(1);
        table_main.setWidthPercentage(90);

        try {

            //Cell 1 start Name and Description
            PdfPCell cell1 = new PdfPCell();
            cell1.setBorder(Rectangle.NO_BORDER);

            Paragraph pName = new Paragraph(user_information.getName(), t_header);
            pName.setAlignment(Element.ALIGN_LEFT);

            Paragraph pDescription = new Paragraph(user_information.getSummary(), t_header);
            pDescription.setAlignment(Element.ALIGN_LEFT);

            Paragraph mPhoneUser=new Paragraph(user_information.getPhoneNumber(),t_item);
            mPhoneUser.setAlignment(Element.ALIGN_LEFT);

            Paragraph mEmail=new Paragraph(user_information.getEmail(),t_item);
            mEmail.setAlignment(Element.ALIGN_LEFT);

            cell1.addElement(pName);
            cell1.addElement(mPhoneUser);
            cell1.addElement(mEmail);

            if(!user_information.getSocial1().isEmpty() && !user_information.getSocial2().isEmpty()){

                Paragraph mSocialUser=new Paragraph(user_information.getSocial1(),t_item);
                mSocialUser.setAlignment(Element.ALIGN_LEFT);
                cell1.addElement(mSocialUser);

                Paragraph mSocialUser2=new Paragraph(user_information.getSocial2(),t_item);
                mSocialUser2.setAlignment(Element.ALIGN_LEFT);
                cell1.addElement(mSocialUser2);
            }
            else if(!user_information.getSocial1().isEmpty()){

                Paragraph mSocialUser=new Paragraph(user_information.getSocial1(),t_item);
                mSocialUser.setAlignment(Element.ALIGN_LEFT);
                cell1.addElement(mSocialUser);
            }
            else if(!user_information.getSocial2().isEmpty()){

                Paragraph mSocialUser2=new Paragraph(user_information.getSocial2(),t_item);
                mSocialUser2.setAlignment(Element.ALIGN_LEFT);
                cell1.addElement(mSocialUser2);
            }

            cell1.addElement(pDescription);

            table_main.addCell(cell1);
            //Cell1 end

            //Cell 2
            PdfPCell cell2=new PdfPCell();
            cell2.setBorder(Rectangle.NO_BORDER);

            //Sorting the experiences by label and printing
            ArrayList<Experience> workExpList=new ArrayList<>();

            for(Experience  e: user_experiences){
                if(e.getLabel().equals("Work Experience")){
                    workExpList.add(e);
                }
            }

            if(workExpList.size()!=0){

                //Adding label
                Paragraph mWorkLabel=new Paragraph("Work Experience",t_header);
                mWorkLabel.setAlignment(Element.ALIGN_LEFT);
                cell2.addElement(linebreak);
                cell2.addElement(mWorkLabel);
                cell2.addElement(linebreak);

                //Inner Table
                PdfPTable innerTable=new PdfPTable(2);
                innerTable.setWidthPercentage(80);
                innerTable.setWidths(colWidths);

                for(Experience e1:workExpList){

                    PdfPCell iCell1=new PdfPCell(); iCell1.setBorder(Rectangle.NO_BORDER);
                    PdfPCell iCell2=new PdfPCell(); iCell2.setBorder(Rectangle.NO_BORDER);

                    iCell1.addElement(new Paragraph(e1.getStart_date(),t_item));
                    iCell2.addElement(new Paragraph(e1.getTitle()+", "+e1.getCompany_name(),t_item));

                    String[] highlightList={e1.getHighlight1(),e1.getHighlight2(),e1.getHighlight3()};

                    for(int i=0;i<3;i++){
                        if(highlightList[i]!=""){
                            List jobNotes=new List(List.UNORDERED);
                            jobNotes.add(new ListItem(highlightList[i],t_item));
                            iCell2.addElement(jobNotes);
                        }
                    }

                    innerTable.addCell(iCell1); innerTable.addCell(iCell2);
                }

                //Pooling Everything
                cell2.addElement(innerTable);
            }

//Sorting the experiences by label and printing
            ArrayList<Experience> EduExpList=new ArrayList<>();

            for(Experience  e: user_experiences){
                if(e.getLabel().equals("Education")){
                    EduExpList.add(e);
                }
            }

            if(EduExpList.size()!=0){

                //Adding label
                Paragraph mEduLabel=new Paragraph("Education",t_header);
                mEduLabel.setAlignment(Element.ALIGN_LEFT);
                cell2.addElement(linebreak);
                cell2.addElement(mEduLabel);
                cell2.addElement(linebreak);

                //Inner Table
                PdfPTable innerTable2=new PdfPTable(2);
                innerTable2.setWidthPercentage(80);
                innerTable2.setWidths(colWidths);


                for(Experience e1:EduExpList){

                    PdfPCell iCell12=new PdfPCell(); iCell12.setBorder(Rectangle.NO_BORDER);
                    PdfPCell iCell22=new PdfPCell(); iCell22.setBorder(Rectangle.NO_BORDER);

                    iCell12.addElement(new Paragraph(e1.getStart_date(),t_item));
                    iCell22.addElement(new Paragraph(e1.getTitle()+", "+e1.getCompany_name(),t_item));

                    String[] highlightList={e1.getHighlight1(),e1.getHighlight2(),e1.getHighlight3()};

                    for(int i=0;i<3;i++){
                        if(highlightList[i]!=""){
                            List jobNotes=new List(List.UNORDERED);
                            jobNotes.add(new ListItem(highlightList[i],t_item));
                            iCell22.addElement(jobNotes);
                        }
                    }
                    innerTable2.addCell(iCell12); innerTable2.addCell(iCell22);
                }

                //Pooling Everything
                cell2.addElement(innerTable2);
            }

            ArrayList<Experience> OtherExpList=new ArrayList<>();

            for(Experience  e: user_experiences){
                if(e.getLabel().equals("Extra Activities")){
                    OtherExpList.add(e);
                }
            }

            if(OtherExpList.size()!=0){

                //Adding label
                Paragraph mExtraLabel=new Paragraph("Extra Activities",t_header);
                mExtraLabel.setAlignment(Element.ALIGN_LEFT);
                cell2.addElement(linebreak);
                cell2.addElement(mExtraLabel);
                cell2.addElement(linebreak);

                //Inner Table
                PdfPTable innerTable3=new PdfPTable(2);
                innerTable3.setWidthPercentage(80);
                innerTable3.setWidths(colWidths);

                for(Experience e1:OtherExpList){

                    PdfPCell iCell13=new PdfPCell(); iCell13.setBorder(Rectangle.NO_BORDER);
                    PdfPCell iCell23=new PdfPCell(); iCell23.setBorder(Rectangle.NO_BORDER);

                    iCell13.addElement(new Paragraph(e1.getStart_date(),t_item));
                    iCell23.addElement(new Paragraph(e1.getTitle()+", "+e1.getCompany_name(),t_item));

                    String[] highlightList={e1.getHighlight1(),e1.getHighlight2(),e1.getHighlight3()};

                    for(int i=0;i<3;i++){
                        if(highlightList[i]!=""){
                            List jobNotes=new List(List.UNORDERED);
                            jobNotes.add(new ListItem(highlightList[i],t_item));
                            iCell23.addElement(jobNotes);
                        }
                    }
                    innerTable3.addCell(iCell13); innerTable3.addCell(iCell23);
                }



                //Pooling Everything
                cell2.addElement(innerTable3);
            }

            //Adding two cells to main table
            table_main.addCell(cell2);

            //Creating the document
            Document mDoc2=new Document(PageSize.A4);

            PdfWriter.getInstance(mDoc2,new FileOutputStream(mFilePath));
            mDoc2.open();

            mDoc2.addAuthor("Wazi Ullah");

            mDoc2.add(table_main);

            mDoc2.close();



        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //complex with color
    public void  makeVariant2(){

        //Making  main table
        float[] colWidths={1f,2f};

        Chunk linebreak=new Chunk(new LineSeparator());

        try{
            PdfPTable table_main=new PdfPTable(2);
            table_main.setWidthPercentage(90);
            table_main.setWidths(colWidths);

            //Cell1 Name and personal  information
            Font t_header=new Font(Font.FontFamily.TIMES_ROMAN,13.0f,Font.BOLD , BaseColor.WHITE);
            Font t_item=new Font(Font.FontFamily.TIMES_ROMAN,10.0f,Font.NORMAL ,BaseColor.WHITE);

            PdfPCell cell1_main=new PdfPCell();
            cell1_main.setBorder(Rectangle.NO_BORDER); cell1_main.setBackgroundColor(BaseColor.DARK_GRAY);

            Paragraph mName=new Paragraph(user_information.getName(),new Font(Font.FontFamily.TIMES_ROMAN,18.0f,Font.BOLD ,BaseColor.WHITE));
            mName.setAlignment(Element.ALIGN_CENTER);

            Paragraph mLabel=new Paragraph("Personal Information",t_header);
            mLabel.setAlignment(Element.ALIGN_CENTER);

            Paragraph mPhone=new Paragraph("Phone",t_header);
            mPhone.setAlignment(Element.ALIGN_CENTER);

            Paragraph mPhoneUser=new Paragraph(user_information.getPhoneNumber(),t_item);
            mPhoneUser.setAlignment(Element.ALIGN_CENTER);

            Paragraph mEmail=new Paragraph("Email",t_header);
            mEmail.setAlignment(Element.ALIGN_CENTER);

            Paragraph mEmailUser=new Paragraph(user_information.getEmail(),t_item);
            mEmailUser.setAlignment(Element.ALIGN_CENTER);

            //Pooling everything
            cell1_main.addElement(mName);
            cell1_main.addElement(linebreak);
            cell1_main.addElement(mLabel);
            cell1_main.addElement(linebreak);
            cell1_main.addElement(mPhone);
            cell1_main.addElement(mPhoneUser);
            cell1_main.addElement(mEmail);
            cell1_main.addElement(mEmailUser);

            if(!user_information.getSocial1().isEmpty() && !user_information.getSocial2().isEmpty()){
                Paragraph mSocial=new Paragraph("Socials",t_header);
                mSocial.setAlignment(Element.ALIGN_CENTER);
                cell1_main.addElement(mSocial);

                Paragraph mSocialUser=new Paragraph(user_information.getSocial1(),t_item);
                mSocialUser.setAlignment(Element.ALIGN_CENTER);
                cell1_main.addElement(mSocialUser);

                Paragraph mSocialUser2=new Paragraph(user_information.getSocial2(),t_item);
                mSocialUser2.setAlignment(Element.ALIGN_CENTER);
                cell1_main.addElement(mSocialUser2);
            }
            else if(!user_information.getSocial1().isEmpty()){
                Paragraph mSocial=new Paragraph("Socials",t_header);
                mSocial.setAlignment(Element.ALIGN_CENTER);
                cell1_main.addElement(mSocial);

                Paragraph mSocialUser=new Paragraph(user_information.getSocial1(),t_item);
                mSocialUser.setAlignment(Element.ALIGN_CENTER);
                cell1_main.addElement(mSocialUser);
            }
            else if(!user_information.getSocial2().isEmpty()){
                Paragraph mSocial=new Paragraph("Socials",t_header);
                mSocial.setAlignment(Element.ALIGN_CENTER);
                cell1_main.addElement(mSocial);

                Paragraph mSocialUser2=new Paragraph(user_information.getSocial2(),t_item);
                mSocialUser2.setAlignment(Element.ALIGN_CENTER);
                cell1_main.addElement(mSocialUser2);
            }

            //Cell2 Summary and Experience
            Font t_headerB=new Font(Font.FontFamily.TIMES_ROMAN,13.0f,Font.BOLD ,BaseColor.BLACK);
            Font t_itemB=new Font(Font.FontFamily.TIMES_ROMAN,10.0f,Font.NORMAL ,BaseColor.BLACK);

            PdfPCell cell2_main=new PdfPCell(); cell2_main.setBorder(Rectangle.NO_BORDER);

            //Summary
            Paragraph pDescription=new Paragraph(user_information.getSummary(),t_headerB);
            pDescription.setAlignment(Element.ALIGN_CENTER);
            cell2_main.addElement(pDescription);

            //Sorting the experiences by label and printing
            ArrayList<Experience> workExpList=new ArrayList<>();

            for(Experience  e: user_experiences){
                if(e.getLabel().equals("Work Experience")){
                    workExpList.add(e);
                }
            }

            if(workExpList.size()!=0){

                //Adding label
                Paragraph mWorkLabel=new Paragraph("Work Experience",t_headerB);
                mWorkLabel.setAlignment(Element.ALIGN_LEFT);
                cell2_main.addElement(linebreak);
                cell2_main.addElement(mWorkLabel);
                cell2_main.addElement(linebreak);

                //Inner Table
                PdfPTable innerTable=new PdfPTable(2);
                innerTable.setWidthPercentage(80);
                innerTable.setWidths(colWidths);

                for(Experience e1:workExpList){

                    PdfPCell iCell1=new PdfPCell(); iCell1.setBorder(Rectangle.NO_BORDER);
                    PdfPCell iCell2=new PdfPCell(); iCell2.setBorder(Rectangle.NO_BORDER);

                    iCell1.addElement(new Paragraph(e1.getStart_date(),t_itemB));
                    iCell2.addElement(new Paragraph(e1.getTitle()+", "+e1.getCompany_name(),t_itemB));

                    String[] highlightList={e1.getHighlight1(),e1.getHighlight2(),e1.getHighlight3()};

                    for(int i=0;i<3;i++){
                        if(highlightList[i]!=""){
                            List jobNotes=new List(List.UNORDERED);
                            jobNotes.add(new ListItem(highlightList[i],t_itemB));
                            iCell2.addElement(jobNotes);
                        }
                    }
                    innerTable.addCell(iCell1); innerTable.addCell(iCell2);
                }
                //Pooling Everything
                cell2_main.addElement(innerTable);
            }

//Sorting the experiences by label and printing
            ArrayList<Experience> EduExpList=new ArrayList<>();

            for(Experience  e: user_experiences){
                if(e.getLabel().equals("Education")){
                    EduExpList.add(e);
                }
            }

            if(EduExpList.size()!=0){

                //Adding label
                Paragraph mEduLabel=new Paragraph("Education",t_headerB);
                mEduLabel.setAlignment(Element.ALIGN_LEFT);
                cell2_main.addElement(linebreak);
                cell2_main.addElement(mEduLabel);
                cell2_main.addElement(linebreak);

                //Inner Table
                PdfPTable innerTable2=new PdfPTable(2);
                innerTable2.setWidthPercentage(80);
                innerTable2.setWidths(colWidths);

                for(Experience e1:EduExpList){

                    PdfPCell iCell12=new PdfPCell(); iCell12.setBorder(Rectangle.NO_BORDER);
                    PdfPCell iCell22=new PdfPCell(); iCell22.setBorder(Rectangle.NO_BORDER);

                    iCell12.addElement(new Paragraph(e1.getStart_date(),t_headerB));
                    iCell22.addElement(new Paragraph(e1.getTitle()+", "+e1.getCompany_name(),t_headerB));

                    String[] highlightList={e1.getHighlight1(),e1.getHighlight2(),e1.getHighlight3()};

                    for(int i=0;i<3;i++){
                        if(highlightList[i]!=""){
                            List jobNotes=new List(List.UNORDERED);
                            jobNotes.add(new ListItem(highlightList[i],t_itemB));
                            iCell22.addElement(jobNotes);
                        }
                    }
                    innerTable2.addCell(iCell12); innerTable2.addCell(iCell22);
                }
                //Pooling Everything
                cell2_main.addElement(innerTable2);
            }

            ArrayList<Experience> OtherExpList=new ArrayList<>();

            for(Experience  e: user_experiences){
                if(e.getLabel().equals("Extra Activities")){
                    OtherExpList.add(e);
                }
            }

            if(OtherExpList.size()!=0){

                //Adding label
                Paragraph mExtraLabel=new Paragraph("Extra Activities",t_headerB);
                mExtraLabel.setAlignment(Element.ALIGN_LEFT);
                cell2_main.addElement(linebreak);
                cell2_main.addElement(mExtraLabel);
                cell2_main.addElement(linebreak);

                //Inner Table
                PdfPTable innerTable3=new PdfPTable(2);
                innerTable3.setWidthPercentage(80);
                innerTable3.setWidths(colWidths);

                for(Experience e1:OtherExpList){

                    PdfPCell iCell13=new PdfPCell(); iCell13.setBorder(Rectangle.NO_BORDER);
                    PdfPCell iCell23=new PdfPCell(); iCell23.setBorder(Rectangle.NO_BORDER);

                    iCell13.addElement(new Paragraph(e1.getStart_date(),t_itemB));
                    iCell23.addElement(new Paragraph(e1.getTitle()+", "+e1.getCompany_name(),t_itemB));

                    String[] highlightList={e1.getHighlight1(),e1.getHighlight2(),e1.getHighlight3()};

                    for(int i=0;i<3;i++){
                        if(highlightList[i]!=""){
                            List jobNotes=new List(List.UNORDERED);
                            jobNotes.add(new ListItem(highlightList[i],t_itemB));
                            iCell23.addElement(jobNotes);
                        }
                    }

                    innerTable3.addCell(iCell13); innerTable3.addCell(iCell23);
                }
                //Pooling Everything
                cell2_main.addElement(innerTable3);
            }

            //Adding two cells to main table

            table_main.addCell(cell1_main);
            table_main.addCell(cell2_main);

            //Creating the document
            Document mDoc2=new Document(PageSize.A4);

            PdfWriter.getInstance(mDoc2,new FileOutputStream(mFilePath));
            mDoc2.open();

            mDoc2.addAuthor("Wazi Ullah");

            mDoc2.add(table_main);

            mDoc2.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
