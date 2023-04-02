package com.pegasus.hk.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.pegasus.hk.client.BotClient;
import com.pegasus.hk.model.request.MessageRequest;
import com.pegasus.hk.model.response.ChatGptResponse;

import lombok.NonNull;

@Service
@Transactional
public class LearningService {

    private final BotClient botClient;

    public LearningService(@NonNull BotClient botClient){
        this.botClient = botClient;
    }

    public List<String> getThemes() {
        return new ArrayList<>(
                Arrays.asList(
                        "Introduction to Personal Finance: Basics of Saving and Budgeting",
                        "Understanding Credit: Building Credit and Managing Debt",
                        "Investing Fundamentals: Types of Investments and Risk Management",
                        "Retirement Planning: Saving for Retirement and Understanding Social Security Benefits",
                        "Taxes and Your Finances: Basic Tax Concepts and Filing Your Taxes",
                        "Advanced Investing: Portfolio Management and Asset Allocation Strategies",
                        "Wealth Management: Strategies for High Net Worth Individuals",
                        "Financial Planning for Business Owners: Cash Flow Management and Business Valuation",
                        "International Finance: Currency Markets and Global Investment Opportunities",
                        "Financial Engineering: Derivatives and Structured Products for Advanced Investors"
                )
        );
    }

    public MessageRequest getThemeMaterial(String theme) {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessage("generate content about " + theme +". Response must be smaller than 16-bit Unicode characters.");
        ChatGptResponse response = botClient.askQuestion(messageRequest);
        Optional.ofNullable(response)
                .flatMap(r -> Optional.ofNullable(r.getChoices()))
                .ifPresent(choices -> {
                    if (!choices.isEmpty()){
                        messageRequest.setMessage(choices.get(0).getText());
                    }
                });
        return messageRequest;
    }
}
